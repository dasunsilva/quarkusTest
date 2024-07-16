package org.dasun.service;

import org.dasun.dto.BillDTO;

import java.util.List;

public interface BillService {
    public List<BillDTO> getAllBills();
    public BillDTO getBill(Long id);
    public String addBill(BillDTO userDTO);
    public String updateBill(BillDTO userDTO, Long id);
    public String deleteBill(Long id);
}
