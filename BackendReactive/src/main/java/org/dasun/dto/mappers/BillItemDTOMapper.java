package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.BillDTO;
import org.dasun.dto.BillItemDTO;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.repo.BillItemRepo;
import org.dasun.repo.BillRepo;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class used to get the mapping between billItem to billItemDTO
 */
@ApplicationScoped
public class BillItemDTOMapper {

    @Inject
    ItemRepo itemRepo;

    @Inject
    BillRepo billRepo;

    /**
     * This method will map the given billItem object to a billItemDTO
     * @param billItems is the use input
     * @return will return a BillItemDTO corresponding to the user input
     */
    public BillItemDTO mapBillItemDTO(BillItems billItems) {
       BillItemDTO billItemDTO = new BillItemDTO();
       billItemDTO.setId(billItems.getId());
       billItemDTO.setName(billItems.getItems().getName());
       billItemDTO.setQuantity(billItems.getQuantity());
       billItemDTO.setPriceAtPurchase(billItems.getPriceAtPurchase());
       billItemDTO.setItemId(billItems.getItems().getId());
       billItemDTO.setBillId(billItems.getBills().getId());
       return billItemDTO;

    }

    /**
     * This method will map the given billItemDTO object to a billItem object
     * @param billItemDTO is the user input
     * @return will return a BillItem object
     */
    public BillItems mapDTOBill(BillItemDTO billItemDTO) {
        BillItems billItems = new BillItems();
        billItems.setId(billItemDTO.getId());
        billItems.setQuantity(billItemDTO.getQuantity());
        billItems.setPriceAtPurchase(billItemDTO.getPriceAtPurchase());
        billItems.setBills(billRepo.findById(billItemDTO.getBillId()));
        billItems.setItems(itemRepo.findById(billItemDTO.getItemId()));
        return billItems;

    }
}
