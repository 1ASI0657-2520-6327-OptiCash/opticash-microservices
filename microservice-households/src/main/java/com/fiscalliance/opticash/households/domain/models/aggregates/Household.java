package com.fiscalliance.opticash.households.domain.models.aggregates;


import com.fiscalliance.opticash.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Household extends AuditableAbstractAggregateRoot<Household> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private com.example.spliteasybackend.households.domain.models.valueobjects.Name name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 10)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "representante_id", nullable = false)
    private User representante;

    protected Household() {}

    public static Household crear(CreateHouseholdCommand command, User representante) {
        if (!representante.isRepresentante()) {
            throw new IllegalArgumentException("El usuario debe tener rol REPRESENTANTE");
        }

        return new Household(command, representante);
    }

    private Household(CreateHouseholdCommand command, User representante) {
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

    public void transferirRepresentacionA(User nuevoRepresentante) {
        if (!nuevoRepresentante.isRepresentante()) {
            throw new IllegalArgumentException("El nuevo representante debe tener rol REPRESENTANTE");
        }
        this.representante = nuevoRepresentante;
    }
}
