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
        billDTO.setItems(bill.getItems());
        billDTO.setUser(bill.getUser());
        return billDTO;

    }

    public Bill mapDTOBill(BillDTO billDTO) {
        Bill bill = new Bill();

        bill.setId(billDTO.getId());
        bill.setDate(billDTO.getDate());
        bill.setAmount(billDTO.getAmount());
        bill.setItems(billDTO.getItems());
        bill.setUser(billDTO.getUser());
        User tempUser = userRepo.findById(billDTO.getUserId());
        bill.setUser(tempUser);

        List<Item> itemList = new ArrayList<>();
        for (Long itemId : billDTO.getItemId()){
            itemList.add(itemRepo.findById(itemId));
        }
        bill.setItems(itemList);
        return bill;

    }
}
