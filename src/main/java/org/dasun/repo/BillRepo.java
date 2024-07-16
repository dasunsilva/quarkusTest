package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.Bill;

import java.util.List;

@ApplicationScoped
public class BillRepo implements PanacheRepository<Bill> {

    public List<Bill> getBillList() {
        return listAll();
    }

    public Bill findByID(Long id){
        return findById(id);
    }

    @Transactional
    public String addBill(Bill bill) {
        try {
            persist(bill);
            return "Bill is added succesfully";
        }catch (Exception e){
            return "Bill is not added. " + e.getMessage();
        }
    }

    @Transactional
    public String updateBill(Bill bill, Long id){
        Bill newBill = findById(id);
        newBill.setDate(bill.getDate());
        newBill.setAmount(bill.getAmount());
        try{
            persist(newBill);
            return "Bill is updated succesfully";
        }catch (Exception e){
            return "Bill is not updated. " + e.getMessage();
        }
    }

    @Transactional
    public String deleteBill(Long id) {
        Bill tempBill = findByID(id);
        try {
            delete(tempBill);
            return "Bill is deleted succesfully";
        }catch (Exception e){
            return "Bill delete failed. " + e.getMessage();
        }
    }
}
