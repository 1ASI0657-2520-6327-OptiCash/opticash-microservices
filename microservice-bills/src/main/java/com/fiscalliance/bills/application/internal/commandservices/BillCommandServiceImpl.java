package com.fiscalliance.bills.application.internal.commandservices;

import com.fiscalliance.bills.domain.models.aggregates.Bill;
import com.fiscalliance.bills.domain.models.commands.CreateBillCommand;
import com.fiscalliance.bills.domain.models.valueobjects.HouseholdId;
import com.fiscalliance.bills.domain.models.valueobjects.UserId;
import com.fiscalliance.bills.domain.services.BillCommandService;
//import com.fiscalliance.bills.infrastructure.clients.HouseholdClient;
//import com.fiscalliance.bills.infrastructure.clients.IamClient;
import com.fiscalliance.bills.infrastructure.persistance.jpa.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillCommandServiceImpl implements BillCommandService {

    private final BillRepository billRepository;
    //private final HouseholdClient householdClient;
   // private final IamClient iamClient;

    public BillCommandServiceImpl(
            BillRepository billRepository
            //HouseholdClient householdClient,
           // IamClient iamClient
    ) {
        this.billRepository = billRepository;
        //this.householdClient = householdClient;
       // this.iamClient = iamClient;
    }

    @Override
    public Optional<Bill> handle(CreateBillCommand command) {
       // if (!householdClient.householdExists(command.householdId()))
       //     throw new IllegalArgumentException("Household not found");

       // if (!iamClient.userExists(command.createdBy()))
         //   throw new IllegalArgumentException("User (creator) not found");

        var bill = Bill.create(command, new HouseholdId(command.householdId()),
                new UserId(command.createdBy()));
        billRepository.save(bill);

        return Optional.of(bill);
    }

    @Override
    public Optional<Bill> update(Long id, CreateBillCommand command) {
        var optional = billRepository.findById(id);
        if (optional.isEmpty()) return Optional.empty();

      //  if (!householdClient.householdExists(command.householdId()))
        //    throw new IllegalArgumentException("Household not found");

      //  if (!iamClient.userExists(command.createdBy()))
        //    throw new IllegalArgumentException("User (creator) not found");

        var bill = optional.get();
        bill.update(command, new HouseholdId(command.householdId()),
                new UserId(command.createdBy()));
        billRepository.save(bill);

        return Optional.of(bill);
    }

    @Override
    public boolean delete(Long id) {
        if (!billRepository.existsById(id)) return false;
        billRepository.deleteById(id);
        return true;
    }
}
