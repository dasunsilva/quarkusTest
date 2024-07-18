package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dasun.dto.BillDTO;
import org.dasun.dto.mappers.BillDTOMapper;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;
import org.dasun.model.Item;
import org.dasun.repo.BillItemRepo;
import org.dasun.repo.BillRepo;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;
import org.dasun.service.BillService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@ApplicationScoped
public class BillServiceImpl implements BillService {

    @Inject
    ItemRepo itemRepo;

    @Inject
    BillRepo billRepo;

    @Inject
    BillDTOMapper billDTOMapper;

    @Inject
    BillItemRepo billItemRepo;

    @Override
    public List<BillDTO> getAllBills() {
        // Get all bills as a list of DTOs
        List<Bill> tempBill = billRepo.listAll();
        List<BillDTO> billDTOList = new ArrayList<>();
        for (Bill bill : tempBill) {
            billDTOList.add(billDTOMapper.mapBillDTO(bill));
        }
        return billDTOList;

    }

    @Override
    public BillDTO getBill(Long id) {
        // Get bill using ID
        Bill tempBill = billRepo.findById(id);
        return billDTOMapper.mapBillDTO(tempBill);
    }

    @Transactional
    @Override
    public String addBill(BillDTO billDTO) {
        // Create a bill using DTO given
        Bill tempBill = billDTOMapper.mapDTOBill(billDTO);

        List<BillItems> billItemsList = new ArrayList<>();
        BillItems billItems = new BillItems();
        billItems.setBills(tempBill);
        billItems.setQuantity(billDTO.getQuantity());

        for(Long itemId:billDTO.getItemId()){
            Item item = itemRepo.findById(itemId);
            billItems.setItems(item);
            billItemsList.add(billItems);
            try { // Save bill_item
                billItemRepo.persist(billItems);
            }catch (Exception e){
                return e.getMessage();
            }
        }

        tempBill.setBillItems(billItemsList);

        try { // Save bill
            billRepo.persist(tempBill);
            return "Bill is added succesfully";
        }catch (Exception e){
            return "Bill is not added. " + e.getMessage();
        }
    }

    @Transactional
    @Override
    public String updateBill(BillDTO billDTO, Long id) {
        // We create a new bill using the DTO given
        Bill newBill = billRepo.findById(id);
        newBill.setDate(billDTO.getDate());
        newBill.setAmount(billDTO.getAmount());


        List<BillItems> billItemsList = new ArrayList<>();
        Bill tempBill = billDTOMapper.mapDTOBill(billDTO);
        BillItems billItems = new BillItems();
        billItems.setBills(tempBill);
        billItems.setQuantity(billDTO.getQuantity());

        for(Long itemId:billDTO.getItemId()){
            Item item = itemRepo.findById(itemId);
            billItems.setItems(item);
            billItemsList.add(billItems);
            try { // Save bill_item
                billItemRepo.persist(billItems);
            }catch (Exception e){
                return e.getMessage();
            }
        }

        newBill.setBillItems(billItemsList);


        try{// Save the new bill aka updatw
            billRepo.persist(newBill);
            return "Bill is updated succesfully";
        }catch (Exception e){
            return "Bill is not updated. " + e.getMessage();
        }
    }

    @Transactional
    @Override
    public String deleteBill(Long id) {
        Bill tempBill = billRepo.findById(id);
        try { // Delete the tempBill
            billRepo.delete(tempBill);
            return "Bill is deleted succesfully";
        }catch (Exception e){
            return "Bill delete failed. " + e.getMessage();
        }
    }
}
