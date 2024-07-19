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

@ApplicationScoped
public class BillItemDTOMapper {

    @Inject
    ItemRepo itemRepo;

    @Inject
    BillRepo billRepo;

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
