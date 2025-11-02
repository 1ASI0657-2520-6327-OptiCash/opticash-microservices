package com.fiscalliance.bills.domain.services;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import com.fiscalliance.bills.domain.models.queries.GetAllBillsQuery;
import com.fiscalliance.bills.domain.models.queries.GetBillByIdQuery;


import java.util.List;
import java.util.Optional;

public interface BillQueryService {
    Optional<Bill> handle(GetBillByIdQuery query);
    List<Bill> handle(GetAllBillsQuery query);
}
