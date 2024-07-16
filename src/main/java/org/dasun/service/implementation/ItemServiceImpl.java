package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.mappers.ItemDTOMapper;
import org.dasun.model.Item;
import org.dasun.repo.ItemRepo;
import org.dasun.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {

    @Inject
    ItemRepo itemRepo;

    @Inject
    ItemDTOMapper itemDTOMapper;

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> tempItem = itemRepo.getItemList();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : tempItem) {
            itemDTOList.add(itemDTOMapper.mapItemDTO(item));
        }
        return itemDTOList;
    }

    @Override
    public ItemDTO getItem(Long id) {
        Item tempItem = itemRepo.findByID(id);
        return itemDTOMapper.mapItemDTO(tempItem);
    }

    @Override
    public String addItem(ItemDTO itemDTO) {
        try{
            Item tempItem = itemDTOMapper.mapDTOItem(itemDTO);
            itemRepo.addItem(tempItem);
            return "Item added successfully";
        }catch (Exception e){
            return "Item could not be added. " + e.getMessage();
        }
    }

    @Override
    public String updateItem(ItemDTO itemDTO, Long id) {
        try{
            Item tempItem = itemDTOMapper.mapDTOItem(itemDTO);
            itemRepo.updateItem(tempItem,id);
            return "Item updated successfully";
        }catch (Exception e){
            return "Item could not be updated. " + e.getMessage();
        }
    }

    @Override
    public String deleteItem(Long id) {
        return itemRepo.deleteItem(id);
    }
}
