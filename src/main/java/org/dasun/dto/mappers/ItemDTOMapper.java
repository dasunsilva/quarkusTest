package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.ItemDTO;
import org.dasun.model.Item;

@ApplicationScoped
public class ItemDTOMapper {

    public ItemDTO mapItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setStock(item.getStock());

        return itemDTO;

    }

    public Item mapDTOItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setStock(itemDTO.getStock());

        return item;

    }
}
