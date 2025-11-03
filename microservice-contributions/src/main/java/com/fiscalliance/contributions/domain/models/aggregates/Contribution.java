package com.fiscalliance.contributions.domain.models.aggregates;
import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.models.valueobjects.Strategy;

import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Contribution extends AuditableAbstractAggregateRoot<Contribution> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    private Long billId;          // antes: Bill bill

    @Column(nullable = false)
    private Long householdId;     // antes: Household household

    @Column(nullable = false, length = 255)
    private String description;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)

    private Strategy strategy;

    protected Contribution() {}

    private Contribution(Long billId, Long householdId, String description, LocalDate fechaLimite, Strategy strategy) {
        this.billId = billId;
        this.householdId = householdId;
        this.description = description;
        this.fechaLimite = fechaLimite;
        this.strategy = strategy;
    }

    public static Contribution create(CreateContributionCommand cmd) {
        return new Contribution(cmd.billId(), cmd.householdId(), cmd.description(), cmd.fechaLimite(), cmd.strategy());
    }

    public void update(CreateContributionCommand cmd) {
        this.billId = cmd.billId();
        this.householdId = cmd.householdId();
        this.description = cmd.description();
        this.fechaLimite = cmd.fechaLimite();
        this.strategy = cmd.strategy();
    }

}
