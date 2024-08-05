package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.ItemDTO;
import org.dasun.model.BillItems;
import org.dasun.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the mapping between item object and itemDTO object\
 */
@ApplicationScoped
public class ItemDTOMapper {

    /**
     * This method will map the given Item object to a itemDTO
     * @param item is the input from the repository
     * @return this will return a itemDTO corresponding to the given item
     */
    public ItemDTO mapItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setStock(item.getStock());
        List<Long> billIds = new ArrayList<>();
        for (BillItems billItem : item.getBillItems()){
            billIds.add(billItem.getItems().getId());
        }
        itemDTO.setBillIds(billIds);
        return itemDTO;

    }

    /**
     * This method will define the mapping between ItemDTO and item object
     * @param itemDTO will be the user input
     * @return this will return a Item object which can be used to store the item in the database
     */
    public Item mapDTOItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setStock(itemDTO.getStock());

        return item;

    }
}
