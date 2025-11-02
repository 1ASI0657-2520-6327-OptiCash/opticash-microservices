package com.fiscalliance.bills.infrastructure.persistance.jpa.repositories;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    // No necesitas más métodos si solo usas ID
}
