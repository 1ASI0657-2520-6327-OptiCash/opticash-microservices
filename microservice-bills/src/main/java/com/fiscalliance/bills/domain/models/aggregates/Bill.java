package com.fiscalliance.bills.domain.models.aggregates;

import com.fiscalliance.bills.domain.models.commands.CreateBillCommand;
import com.fiscalliance.bills.domain.models.valueobjects.HouseholdId;
import com.fiscalliance.bills.domain.models.valueobjects.Money;
import com.fiscalliance.bills.domain.models.valueobjects.UserId;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Bill extends AuditableAbstractAggregateRoot<Bill> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    @AttributeOverride(name = "householdId", column = @Column(name = "household_id", nullable = false))
    private HouseholdId householdId;

    @Column(nullable = false, length = 255)
    private String description;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "monto", nullable = false))
    private Money monto;

    @Getter
    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "created_by", nullable = false))
    private UserId createdBy;

    @Column(nullable = false)
    private LocalDate fecha;

    protected Bill() {
        // Requerido por JPA
    }

    private Bill(HouseholdId household, String description, Money monto, UserId createdBy, LocalDate fecha) {
        this.householdId = household;
        this.description = description;
        this.monto = monto;
        this.createdBy = createdBy;
        this.fecha = fecha;
    }

    public static Bill create(CreateBillCommand command, HouseholdId household, UserId creator) {
        return new Bill(
                household,
                command.description(),
                command.monto(),
                creator,
                command.fecha()
        );
    }

    public void update(CreateBillCommand command, HouseholdId household, UserId creator) {
        this.householdId = household;
        this.description = command.description();
        this.monto = command.monto();
        this.createdBy = creator;
        this.fecha = command.fecha();
    }
}
