package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.BillDTO;
import org.dasun.dto.BillItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.repo.BillRepo;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the mappings between bill and billDTO
 */
@ApplicationScoped
public class BillDTOMapper {

    @Inject
    UserRepo userRepo;

    @Inject
    ItemRepo itemRepo;

    @Inject
    BillRepo billRepo;

    @Inject
    UserDTOMapper userDTOMapper;

    /**
     * This will take a bill object and return a billDTO
     * @param bill is the bill object
     * @return will be a billDTO
     */
    public BillDTO mapBillDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();

        billDTO.setId(bill.getId());
        billDTO.setDate(bill.getDate());
        billDTO.setAmount(bill.getAmount());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(bill.getUser().getId());
        userDTO.setName(bill.getUser().getName());
        userDTO.setPhone(bill.getUser().getPhoneNumber());
        userDTO.setEmail(bill.getUser().getEmail());
        billDTO.setUserDTO(userDTO);

        List<BillItemDTO> billItemDTOList = new ArrayList<>();
        for(BillItems billItems: bill.getBillItems()) {
            BillItemDTO tempBillItemDTO = new BillItemDTO();
            tempBillItemDTO.setId(billItems.getId());
            tempBillItemDTO.setName(billItems.getItems().getName());
            tempBillItemDTO.setQuantity(billItems.getQuantity());
            tempBillItemDTO.setPriceAtPurchase(billItems.getPriceAtPurchase());
            billItemDTOList.add(tempBillItemDTO);
        }
        billDTO.setBillItemDTOS(billItemDTOList);
        return billDTO;
    }

    /**
     * This defines the mapping between billDTO and Bill
     * @param billDTO is the user input
     * @return will be a bill object corresponding to billDTO
     */
    public Bill mapDTOBill(BillDTO billDTO) {
        Bill bill = new Bill();

        bill.setId(billDTO.getId());
        bill.setDate(billDTO.getDate());
        bill.setAmount(billDTO.getAmount());
        bill.setUser(userRepo.findById(billDTO.getUserId()));

        List<BillItems> billItemsList = new ArrayList<>();
        for(BillItemDTO billItemDTO: billDTO.getBillItemDTOS()) {
            BillItems billItems = new BillItems();
            billItems.setId(billItemDTO.getId());
            billItems.setQuantity(billItemDTO.getQuantity());
            billItems.setPriceAtPurchase(billItemDTO.getPriceAtPurchase());
            billItems.setItems(itemRepo.findById(billItemDTO.getItemId()));
            billItemsList.add(billItems);
        }
        bill.setBillItems(billItemsList);
        return bill;
    }
}
