package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dasun.dto.BillDTO;
import org.dasun.dto.BillItemDTO;
import org.dasun.dto.mappers.BillDTOMapper;
import org.dasun.dto.mappers.BillItemDTOMapper;
import org.dasun.exceptions.DatabaseException;
import org.dasun.exceptions.InvalidLongException;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;
import org.dasun.model.Item;
import org.dasun.model.User;
import org.dasun.repo.BillItemRepo;
import org.dasun.repo.BillRepo;
import org.dasun.repo.ItemRepo;
import org.dasun.repo.UserRepo;
import org.dasun.service.BillService;
import org.hibernate.dialect.Database;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@ApplicationScoped
public class BillServiceImpl implements BillService {

    @Inject
    UserRepo userRepo;

    @Inject
    BillRepo billRepo;

    @Inject
    BillDTOMapper billDTOMapper;

    @Inject
    BillItemRepo billItemRepo;

    @Inject
    BillItemDTOMapper billItemDTOMapper;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public BillDTO getBill(Long id) throws InvalidLongException{
        // Get bill using ID
            Bill tempBill = billRepo.findById(id);
            return billDTOMapper.mapBillDTO(tempBill);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String addBill(BillDTO billDTO) throws DatabaseException {
        // Create a bill using DTO given
        Bill tempBill = billDTOMapper.mapDTOBill(billDTO);

        try { // Save bill
            billRepo.persist(tempBill);
            for(BillItems billItems : tempBill.getBillItems()){
                billItems.setBills(tempBill);
            }
            billItemRepo.persist(tempBill.getBillItems());
            return "Bill added successfully";
        }catch (Exception e){
            throw new DatabaseException("Error when adding the bill");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String updateBill(BillDTO billDTO, Long id) throws DatabaseException{

        // TODO : Find a better implementation for this

        Bill existingBill = billRepo.findById(id);
        existingBill.setDate(billDTO.getDate());
        existingBill.setAmount(billDTO.getAmount());
        existingBill.setUser(userRepo.findById(billDTO.getUserId()));

        for (BillItems billItems : existingBill.getBillItems()) {
            billItemRepo.delete(billItems);
        }

        List<BillItems> billItemsList = new ArrayList<>();
        for (BillItemDTO billItemDTO : billDTO.getBillItemDTOS()) {
            billItemDTO.setBillId(existingBill.getId());
            BillItems billItem = billItemDTOMapper.mapDTOBill(billItemDTO);
            billItemsList.add(billItem);
        }
        existingBill.setBillItems(billItemsList);

        try { // Save bill
            billRepo.persist(existingBill);
            return "Bill updated successfully";
        }catch (Exception e){
            throw new DatabaseException("Error when updating the bill");

        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String deleteBill(Long id) throws DatabaseException{
        Bill tempBill = billRepo.findById(id);
        try { // Delete the tempBill
            billRepo.delete(tempBill);
            return "Bill is deleted succesfully";
        }catch (Exception e){
            throw new DatabaseException("Error when deleting the bill");

        }
    }
}
