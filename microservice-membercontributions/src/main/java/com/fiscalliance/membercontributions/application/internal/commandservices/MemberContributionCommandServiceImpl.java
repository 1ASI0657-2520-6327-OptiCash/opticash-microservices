package com.fiscalliance.iam.application.internal.commandservices;

import com.fiscalliance.iam.domain.models.aggregates.MemberContribution;
import com.fiscalliance.iam.domain.models.commands.CreateMemberContributionCommand;
import com.fiscalliance.iam.domain.services.MemberContributionCommandService;
import com.fiscalliance.iam.infrastructure.persistance.jpa.repositories.MemberContributionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberContributionCommandServiceImpl implements MemberContributionCommandService {

    private final MemberContributionRepository memberContributionRepository;

    public MemberContributionCommandServiceImpl(MemberContributionRepository memberContributionRepository) {
        this.memberContributionRepository = memberContributionRepository;
    }

    @Override
    public Optional<MemberContribution> handle(CreateMemberContributionCommand command) {
        MemberContribution entity = new MemberContribution(
                command.contributionId(),
                command.memberId(),
                command.monto()
        );

        memberContributionRepository.save(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<MemberContribution> update(Long id, CreateMemberContributionCommand command) {
        var optional = memberContributionRepository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

        var entity = optional.get();
        entity.update(command);
        memberContributionRepository.save(entity);
        return Optional.of(entity);
    }

    @Override
    public boolean delete(Long id) {
        if (!memberContributionRepository.existsById(id)) return false;
        memberContributionRepository.deleteById(id);
        return true;
    }
}
