package org.dasun.dto.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.dasun.dto.ItemDTO;
import org.dasun.model.Bill;
import org.dasun.model.BillItems;
import org.dasun.model.Item;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemDTOMapper {

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

    public Item mapDTOItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setStock(itemDTO.getStock());

        return item;

    }
}
