package com.fiscalliance.contributions.domain.models.aggregates;

import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.models.valueobjects.BillId;
import com.fiscalliance.contributions.domain.models.valueobjects.HouseholdId;
import com.fiscalliance.contributions.domain.models.valueobjects.Member;
import com.fiscalliance.contributions.domain.models.valueobjects.Strategy;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.fiscalliance.contributions.domain.models.valueobjects.UserIncome;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;


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

    public static Contribution create(CreateContributionCommand command) {
        return new Contribution(
                new BillId(command.billId()),
                new HouseholdId(command.householdId()),
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

    public List<MemberContribution> distribute(BigDecimal totalAmount, List<Member> members, List<UserIncome> userIncomes) {
        if (strategy.equals(Strategy.INCOME_BASED)){
            // Calcular la suma total de ingresos
            var totalIncome = userIncomes.stream()
                    .map(UserIncome::income)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return members.stream()
                    .map(member -> {
                        var userIncome = userIncomes.stream()
                                .filter(u -> u.userId().equals(member.userId()))
                                .map(UserIncome::income)
                                .findFirst()
                                .orElse(BigDecimal.ZERO);

                        var ratio = userIncome.divide(totalIncome, 2, RoundingMode.HALF_UP);
                        var memberAmount = totalAmount.multiply(ratio);
                        return new MemberContribution(this, member.userId(), memberAmount);
                    })
                    .toList();
        }
        // 🔸 Otra estrategia: EQUAL (dividir en partes iguales)
        if (strategy.equals(Strategy.EQUAL)) {
            var perMember = totalAmount.divide(BigDecimal.valueOf(members.size()), 2, RoundingMode.HALF_UP);
            return members.stream()
                    .map(member -> new MemberContribution(this, member.userId(), perMember))
                    .toList();
        }

        // Otro tipo de distribución...
        return List.of();
    }
}
