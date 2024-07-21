package org.dasun.service;

import org.dasun.dto.BillDTO;

import java.util.List;

/**
 * This interface defines the bill related services
 */
public interface BillService {
    /**
     * This method is used to get all the bills from the bill repo
     * @return a list of BillDTO that can be used to display the results.
     */
    public List<BillDTO> getAllBills();

    /**
     * This method is used to get a single bill from the bill repo
     * @param id is the id of the bill that use needs
     * @return a BillDTO that can be used to display the results.
     */
    public BillDTO getBill(Long id);

    /**
     * This method is used to update a bill in the bill repository
     * @param billDTO User input will be taken as a billDTO
     * @param id is the id of the bill that need to be updated
     * @return this will return a string indicating the status of the transaction
     */
    public String updateBill(BillDTO billDTO, Long id);

    /**
     * This method is used to delete a bill in the bill repository
     * @param id is the id of the bill that need to be deleted
     * @return this will return a string indicating the status of the transaction
     */
    public String deleteBill(Long id);

    /**
     * This is used to add a bill to the database
     * @param billDTO is the user input. It will be taken as a billDTO
     * @return this will return a string indicating the status of the transaction
     */
    public String addBill(BillDTO billDTO);
}
