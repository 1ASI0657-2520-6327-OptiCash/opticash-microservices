package com.fiscalliance.households.domain.models.aggregates;

import com.fiscalliance.households.domain.models.commands.CreateHouseholdCommand;
import com.fiscalliance.households.domain.models.valueobjects.Name;
import com.fiscalliance.households.domain.models.valueobjects.UserId;
import com.fiscalliance.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Household extends AuditableAbstractAggregateRoot<Household> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 10)
    private String currency;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "userId", column = @Column(name = "representante_user_id"))
    })
    private UserId representante;

    protected Household() {}

    public static Household crear(CreateHouseholdCommand command, UserId representante) {
        /*
        if (!representante.isRepresentante()) {
            throw new IllegalArgumentException("El usuario debe tener rol REPRESENTANTE");
        }
        */


        return new Household(command, representante);
    }

    private Household(CreateHouseholdCommand command, UserId representante) {
        this.name = new Name(command.name());
        this.description = command.description();
        this.currency = command.currency();
        this.representante = representante;
    }

    public void update(CreateHouseholdCommand command) {
        this.name = new Name(command.name());
        this.description = command.description();
        this.currency = command.currency();
    }

    public void transferirRepresentacionA(UserId nuevoRepresentante) {
        /*
        if (!nuevoRepresentante.isRepresentante()) {
            throw new IllegalArgumentException("El nuevo representante debe tener rol REPRESENTANTE");
        }
        */
        this.representante = nuevoRepresentante;
    }
}
