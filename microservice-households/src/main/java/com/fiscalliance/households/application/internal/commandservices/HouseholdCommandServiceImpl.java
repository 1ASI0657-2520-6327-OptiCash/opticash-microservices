package com.fiscalliance.households.application.internal.commandservices;

import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.domain.models.commands.CreateHouseholdCommand;
import com.fiscalliance.households.domain.models.valueobjects.UserId;
import com.fiscalliance.households.domain.services.HouseholdCommandService;
import com.fiscalliance.households.infrastructure.persistance.jpa.repositories.HouseholdRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseholdCommandServiceImpl implements HouseholdCommandService {

    private final HouseholdRepository householdRepository;

    public HouseholdCommandServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Optional<Household> handle(CreateHouseholdCommand command) {
        // ✅ Obtener al representante por ID
        var representante = new UserId(command.representanteId());

        // ✅ Crear hogar con lógica de negocio
        var household = Household.crear(command, representante);
        householdRepository.save(household);

        return Optional.of(household);
    }

    @Override
    public Optional<Household> update(Long id, CreateHouseholdCommand command) {
        var optional = householdRepository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        var household = optional.get();

        // ⚠️ Validamos si el representante cambió
        if (!household.getRepresentante().userId().equals(command.representanteId())) {
            var nuevoRepresentante = new UserId(command.representanteId());
            household.transferirRepresentacionA(nuevoRepresentante);
        }


        // ✅ Actualiza otros campos (nombre, moneda, descripción)
        household.update(command);
        householdRepository.save(household);

        return Optional.of(household);
    }

    @Override
    public boolean delete(Long id) {
        if (!householdRepository.existsById(id)) return false;
        householdRepository.deleteById(id);
        return true;
    }
}
