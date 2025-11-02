package com.fiscalliance.bills.application.internal.queryservices;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import com.fiscalliance.bills.domain.models.queries.GetAllBillsQuery;
import com.fiscalliance.bills.domain.models.queries.GetBillByIdQuery;
import com.fiscalliance.bills.domain.services.BillQueryService;
import com.fiscalliance.bills.infrastructure.persistance.jpa.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillQueryServiceImpl implements BillQueryService {

    private final BillRepository repository;

    public BillQueryServiceImpl(BillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Bill> handle(GetBillByIdQuery query) {
        return repository.findById(query.id());
    }

    @Override
    public List<Bill> handle(GetAllBillsQuery query) {
        return repository.findAll();
    }
}
