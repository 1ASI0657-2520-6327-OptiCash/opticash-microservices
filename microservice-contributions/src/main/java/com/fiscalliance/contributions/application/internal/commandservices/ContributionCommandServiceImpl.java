package com.fiscalliance.contributions.application.internal.commandservices;

import com.fiscalliance.contributions.domain.models.aggregates.Contribution;
import com.fiscalliance.contributions.domain.models.commands.CreateContributionCommand;
import com.fiscalliance.contributions.domain.services.ContributionCommandService;
import com.fiscalliance.contributions.infrastructure.persistance.jpa.repositories.ContributionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContributionCommandServiceImpl implements ContributionCommandService {


    private final ContributionRepository contributionRepository;

    public ContributionCommandServiceImpl(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Override
    public Optional<Contribution> handle(CreateContributionCommand cmd) {
// Optionally validate cmd.billId/cmd.householdId via Feign before create
        var entity = Contribution.create(cmd);
        contributionRepository.save(entity);
// Optionally publish an event here
        return Optional.of(entity);
    }


    @Override
    public Optional<Contribution> update(Long id, CreateContributionCommand cmd) {
        var opt = contributionRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();
        var entity = opt.get();
        entity.update(cmd);
        contributionRepository.save(entity);
        return Optional.of(entity);
    }


    @Override
    public boolean delete(Long id) {
        if (!contributionRepository.existsById(id)) return false;
        contributionRepository.deleteById(id);
        return true;
    }
}