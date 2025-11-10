package com.fiscalliance.households.domain.models.aggregates;

import com.fiscalliance.households.domain.models.valueobjects.UserId;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Getter
public class HouseholdMember extends AuditableAbstractAggregateRoot<HouseholdMember> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    })
    private UserId userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    protected HouseholdMember() {}

    private HouseholdMember(Household household, UserId userId) {
        this.household = household;
        this.userId = userId;
    }

    public static HouseholdMember create(Household household, UserId userId, boolean alreadyMember) {
        if (alreadyMember) {
            throw new IllegalStateException("El usuario ya es miembro de este hogar.");
        }
        return new HouseholdMember(household, userId);
    }

    public void update(Household household, UserId userId) {
        this.household = household;
        this.userId = userId;
    }
}