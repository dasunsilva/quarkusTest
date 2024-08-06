package org.dasun.command;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.BillDTO;

public interface BillCommand {

    /**
     * This is used to add a bill to the database
     *
     * @param billDTO is the user input. It will be taken as a billDTO
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> addBill(BillDTO billDTO);

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

}
