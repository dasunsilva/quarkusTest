package org.dasun.command;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.ItemDTO;

public interface ItemCommand {
    /**
     * This is used to add a item to the database
     *
     * @param itemDTO is the user input. It will be taken as a billDTO
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> addItem(ItemDTO itemDTO);

    /**
     * This method is used to update a item in the item repository
     *
     * @param itemDTO User input will be taken as a itemDTO
     * @param id      is the id of the item that need to be updated
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> updateItem(ItemDTO itemDTO, Long id);

    /**
     * This method is used to delete a user in the user repository
     *
     * @param id is the id of the user that need to be deleted
     * @return this will return a string indicating the status of the transaction
     */
    public Uni<String> deleteItem(Long id);
}
