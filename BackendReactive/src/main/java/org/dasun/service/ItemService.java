package org.dasun.service;

import io.smallrye.mutiny.Uni;
import org.dasun.dto.ItemDTO;

import java.util.List;
/**
 * This interface defines the item related services
 */
public interface ItemService {
    /**
     * This method is used to get all the items from the item repo
     *
     * @return a list of item that can be used to display the results.
     */
    public Uni<List<ItemDTO>> getAllItems();

    /**
     * This method is used to get a single item information from the item repo
     *
     * @param id is the id of the item that user needs
     * @return a ItemDTO that can be used to display the results.
     */
    public Uni<ItemDTO> getItem(Long id);

}
