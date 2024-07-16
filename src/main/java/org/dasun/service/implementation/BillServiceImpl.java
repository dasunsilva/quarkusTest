package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.BillDTO;
import org.dasun.dto.mappers.BillDTOMapper;
import org.dasun.model.Bill;
import org.dasun.repo.BillRepo;
import org.dasun.service.BillService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@ApplicationScoped
public class BillServiceImpl implements BillService {

    @Inject
    BillRepo billRepo;

    @Inject
    BillDTOMapper billDTOMapper;

    @Override
    public List<BillDTO> getAllBills() {
        List<Bill> tempBill = billRepo.getBillList();
        List<BillDTO> billDTOList = new ArrayList<>();
        for (Bill bill : tempBill) {
            billDTOList.add(billDTOMapper.mapBillDTO(bill));
        }
        return billDTOList;
    }

    @Override
    public BillDTO getBill(Long id) {
        Bill tempBill = billRepo.findByID(id);
        return billDTOMapper.mapBillDTO(tempBill);
    }

    @Override
    public String addBill(BillDTO billDTO) {
        try{
            Bill tempBill = billDTOMapper.mapDTOBill(billDTO);
            billRepo.addBill(tempBill);
            return "Bill added successfully";
        }catch(Exception e){
            return "Bill could not be added";
        }
    }

    @Override
    public String updateBill(BillDTO billDTO, Long id) {
        try{
            Bill tempBill = billDTOMapper.mapDTOBill(billDTO);
            billRepo.updateBill(tempBill,id);
            return "Bill updated successfully";
        }catch(Exception e){
            return "Bill could not be updated";
        }
    }

    @Override
    public String deleteBill(Long id) {
        return billRepo.deleteBill(id);
    }
}
