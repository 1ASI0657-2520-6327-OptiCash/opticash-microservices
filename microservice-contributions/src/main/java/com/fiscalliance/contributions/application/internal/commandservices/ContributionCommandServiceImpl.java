package com.fiscalliance.contributions.application.internal.commandservices;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import com.fiscalliance.bills.infrastructure.persistance.jpa.repositories.BillRepository;
import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.services.ContributionCommandService;
import com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories.ContributionRepository;
import com.fiscalliance.householdmembers.infrastructure.persistance.jpa.repositories.HouseholdMemberRepository;
import com.fiscalliance.households.domain.models.aggregates.Household;
import com.fiscalliance.households.infrastructure.persistance.jpa.repositories.HouseholdRepository;
import com.fiscalliance.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.fiscalliance.membercontributions.infrastructure.persistance.jpa.repositories.MemberContributionRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContributionCommandServiceImpl implements ContributionCommandService {

    private final ContributionRepository contributionRepository;
    private final BillRepository billRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdMemberRepository memberRepository;
    private final UserRepository userRepository;
    private final MemberContributionRepository memberContributionRepository;

    public ContributionCommandServiceImpl(
            ContributionRepository contributionRepository,
            BillRepository billRepository,
            HouseholdRepository householdRepository,
            HouseholdMemberRepository memberRepository,
            UserRepository userRepository,
            MemberContributionRepository memberContributionRepository) {
        this.contributionRepository = contributionRepository;
        this.billRepository = billRepository;
        this.householdRepository = householdRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.memberContributionRepository = memberContributionRepository;
    }

    @Override
    public Optional<Contribution> handle(CreateContributionCommand command) {
        // Obtener bill y household por ID
        Bill bill = billRepository.findById(command.billId())
                .orElseThrow(() -> new IllegalArgumentException("Bill no encontrado"));

        Household household = householdRepository.findById(command.householdId())
                .orElseThrow(() -> new IllegalArgumentException("Household no encontrado"));

        // Crear contribution con lógica de negocio
        Contribution contribution = Contribution.create(command, bill, household);

        // Guardar contribution
        contributionRepository.save(contribution);

        // Distribuir los montos entre miembros
        var members = memberRepository.findAllByHousehold_Id(household.getId());
        contribution.distribute(members, userRepository, memberContributionRepository);

        return Optional.of(contribution);
    }

    @Override
    public Optional<Contribution> update(Long id, CreateContributionCommand command) {
        var contributionOpt = contributionRepository.findById(id);
        if (contributionOpt.isEmpty()) return Optional.empty();

        var contribution = contributionOpt.get();

        Bill bill = billRepository.findById(command.billId())
                .orElseThrow(() -> new IllegalArgumentException("Bill no encontrado"));

        Household household = householdRepository.findById(command.householdId())
                .orElseThrow(() -> new IllegalArgumentException("Household no encontrado"));

        contribution.update(command, bill, household);
        contributionRepository.save(contribution);

        // ❗ Si decides redistribuir en update también:
        var members = memberRepository.findAllByHousehold_Id(household.getId());
        contribution.distribute(members, userRepository, memberContributionRepository);

        return Optional.of(contribution);
    }

    @Override
    public boolean delete(Long id) {
        if (!contributionRepository.existsById(id)) return false;
        contributionRepository.deleteById(id);
        return true;
    }
}
