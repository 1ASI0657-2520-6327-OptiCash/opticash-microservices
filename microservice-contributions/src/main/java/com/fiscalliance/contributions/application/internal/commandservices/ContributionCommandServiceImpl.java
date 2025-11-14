package com.fiscalliance.contributions.application.internal.commandservices;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.models.valueobjects.BillId;
import com.fiscalliance.contributions.domain.models.valueobjects.HouseholdId;
import com.fiscalliance.contributions.domain.models.valueobjects.Member;
import com.fiscalliance.contributions.domain.models.valueobjects.UserIncome;
import com.fiscalliance.contributions.domain.services.ContributionCommandService;
import com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories.ContributionRepository;
import com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories.MemberContributionRepository;
import com.fiscalliance.contributions.infrastructure.rest.BillClient;
import com.fiscalliance.contributions.infrastructure.rest.HouseholdClient;
import com.fiscalliance.contributions.infrastructure.rest.IamClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContributionCommandServiceImpl implements ContributionCommandService {
    private final ContributionRepository contributionRepository;
    private final MemberContributionRepository memberContributionRepository;
    private final HouseholdClient householdClient;
    private final BillClient billClient;
    private final IamClient iamClient;

    public ContributionCommandServiceImpl(
            ContributionRepository contributionRepository,
            MemberContributionRepository memberContributionRepository,
            HouseholdClient householdClient,
            BillClient billClient,
            IamClient iamClient) {
        this.contributionRepository = contributionRepository;
        this.memberContributionRepository = memberContributionRepository;
        this.householdClient = householdClient;
        this.billClient = billClient;
        this.iamClient = iamClient;
    }

    @Override
    public Optional<Contribution> handle(CreateContributionCommand command) {
        var contribution = Contribution.create(command);
        contribution = contributionRepository.save(contribution); // asignar para asegurar id si es necesario

        // 1️⃣ Obtener miembros del hogar (responses)
        var membersResponse = householdClient.getMembersByHousehold(command.householdId());

        // Mapear a value objects de dominio
        var domainMembers = membersResponse.stream()
                .map(m -> new Member(m.userId(), m.householdId()))
                .toList();

        // 2️⃣ Obtener el monto total de la factura
        var totalAmount = billClient.getBillAmount(command.billId());

        // 3️⃣ Obtener los ingresos de los usuarios (responses)
        var userIds = membersResponse.stream()
                .map(m -> m.userId())
                .toList();
        var userIncomesResponse = iamClient.getUsersIncome(userIds);

        // Mapear a value objects de dominio
        var domainUserIncomes = userIncomesResponse.stream()
                .map(u -> new UserIncome(u.userId(), u.income()))
                .toList();

        // 4️⃣ Lógica de distribución usando los value objects del dominio
        var distributedContributions = contribution.distribute(totalAmount, domainMembers, domainUserIncomes);

        // 5️⃣ Guardar las contribuciones de miembros
        memberContributionRepository.saveAll(distributedContributions);

        return Optional.of(contribution);
    }

    @Override
    public Optional<Contribution> update(Long id, CreateContributionCommand command) {
        var optional = contributionRepository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        var existing = optional.get();
        existing.update(
                command,
                new BillId(command.billId()),
                new HouseholdId(command.householdId())
        );

        contributionRepository.save(existing);
        return Optional.of(existing);
    }

    @Override
    public boolean delete(Long id) {
        if (!contributionRepository.existsById(id)) return false;
        contributionRepository.deleteById(id);
        return true;
    }
}
