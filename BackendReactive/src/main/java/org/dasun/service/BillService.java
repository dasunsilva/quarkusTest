package org.dasun.service;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.BillDTO;

import java.util.List;

/**
 * This interface defines the bill related services
 */
public interface BillService {
    /**
     * This method is used to get all the bills from the bill repo
     *
     * @return a list of BillDTO that can be used to display the results.
     */
    public Uni<List<BillDTO>> getAllBills();

    /**
     * This method is used to get a single bill from the bill repo
     *
     * @param id is the id of the bill that use needs
     * @return a BillDTO that can be used to display the results.
     */
    public Uni<BillDTO> getBill(Long id);


}
