package com.fiscalliance.contributions.domain.models.aggregates;



import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.models.valueobjects.BillId;
import com.fiscalliance.contributions.domain.models.valueobjects.HouseholdId;
import com.fiscalliance.contributions.domain.models.valueobjects.Strategy;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Contribution extends AuditableAbstractAggregateRoot<Contribution> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "billId", column = @Column(name = "bill_id", nullable = false))
    })
    private BillId billId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "householdId", column = @Column(name = "household_id", nullable = false))
    })
    private HouseholdId householdId;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Strategy strategy;

    protected Contribution() {
        // Requerido por JPA
    }

    private Contribution(BillId bill, HouseholdId household, String description, LocalDate fechaLimite, Strategy strategy) {
        this.billId = bill;
        this.householdId = household;
        this.description = description;
        this.fechaLimite = fechaLimite;
        this.strategy = strategy;
    }

    public static Contribution create(CreateContributionCommand command, BillId bill, HouseholdId household) {
        return new Contribution(
                bill,
                household,
                command.description(),
                command.fechaLimite(),
                command.strategy()
        );
    }

    public void update(CreateContributionCommand command, BillId bill, HouseholdId household) {

        this.billId = bill;
        this.householdId = household;
        this.description = command.description();
        this.fechaLimite = command.fechaLimite();
        this.strategy = command.strategy();
    }

    public void distribute(
            List<HouseholdMember> members,
            UserRepository userRepository,
            MemberContributionRepository memberContributionRepository) {

        if (members == null || members.isEmpty()) {
            throw new IllegalArgumentException("No hay miembros en el hogar.");
        }

        var totalAmount = this.bill.getMonto().value();

        switch (this.strategy) {
            case EQUAL -> {
                BigDecimal equalAmount = totalAmount
                        .divide(BigDecimal.valueOf(members.size()), 2, RoundingMode.HALF_UP);
                for (var member : members) {
                    var user = userRepository.findById(member.getUser().getId())
                            .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
                    memberContributionRepository.save(new MemberContribution(this, user, equalAmount));
                }
            }

            case INCOME_BASED -> {
                BigDecimal totalIncome = members.stream()
                        .map(member -> userRepository.findById(member.getUser().getId())
                                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"))
                                .getIncome())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                if (totalIncome.compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalStateException("La suma total de ingresos es cero. No se puede distribuir.");
                }

                for (var member : members) {
                    User user = userRepository.findById(member.getUser().getId())
                            .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

                    BigDecimal porcentaje = user.getIncome()
                            .divide(totalIncome, 5, RoundingMode.HALF_UP);
                    BigDecimal montoAsignado = totalAmount.multiply(porcentaje)
                            .setScale(2, RoundingMode.HALF_UP);

                    memberContributionRepository.save(new MemberContribution(this, user, montoAsignado));
                }
            }

            default -> throw new UnsupportedOperationException("Estrategia no soportada: " + this.strategy);
        }
    }

}
