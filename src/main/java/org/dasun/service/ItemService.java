package org.dasun.service;

import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;
import org.dasun.exceptions.DatabaseException;

import java.util.List;
/**
 * This interface defines the item related services
 */
public interface ItemService {
    /**
     * This method is used to get all the items from the item repo
     * @return a list of item that can be used to display the results.
     */
    public List<ItemDTO> getAllItems();

    /**
     * This method is used to get a single item information from the item repo
     * @param id is the id of the item that user needs
     * @return a ItemDTO that can be used to display the results.
     */
    public ItemDTO getItem(Long id);

    /**
     * This is used to add a item to the database
     * @param itemDTO is the user input. It will be taken as a billDTO
     * @return this will return a string indicating the status of the transaction
     */
    public String addItem(ItemDTO itemDTO) throws DatabaseException;

    /**
     * This method is used to update a item in the item repository
     * @param itemDTO User input will be taken as a itemDTO
     * @param id is the id of the item that need to be updated
     * @return this will return a string indicating the status of the transaction
     */
    public String updateItem(ItemDTO itemDTO, Long id) throws DatabaseException;

    /**
     * This method is used to delete a user in the user repository
     * @param id is the id of the user that need to be deleted
     * @return this will return a string indicating the status of the transaction
     */
    public String deleteItem(Long id) throws DatabaseException;
}
