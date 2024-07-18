package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.BillDTO;
import org.dasun.model.Bill;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BillDTOMapper {

    @Inject
    UserRepo userRepo;

    @Inject
    ItemRepo itemRepo;

    public BillDTO mapBillDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();

        billDTO.setId(bill.getId());
        billDTO.setDate(bill.getDate());
        billDTO.setAmount(bill.getAmount());
        billDTO.setUser(bill.getUser());

        // Items List?
        return billDTO;
    }

    public Bill mapDTOBill(BillDTO billDTO) {
        Bill bill = new Bill();

        bill.setId(billDTO.getId());
        bill.setDate(billDTO.getDate());
        bill.setAmount(billDTO.getAmount());
        User tempUser = userRepo.findById(billDTO.getUserId());
        bill.setUser(tempUser);
        return bill;

    }
}
