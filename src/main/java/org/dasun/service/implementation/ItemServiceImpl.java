package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
        // Get all items as a list of DTOs
        List<Item> tempItem = itemRepo.listAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : tempItem) {
            itemDTOList.add(itemDTOMapper.mapItemDTO(item));
        }
        return itemDTOList;
    }

    @Override
    public ItemDTO getItem(Long id) {
        // Get item using ID
        Item tempItem = itemRepo.findById(id);
        return itemDTOMapper.mapItemDTO(tempItem);
    }

    @Transactional
    @Override
    public String addItem(ItemDTO itemDTO) {
        // Create a item using DTO given
        Item tempItem = itemDTOMapper.mapDTOItem(itemDTO);

        try { // Save item
            itemRepo.persist(tempItem);
            return "Item is added succesfully";
        }catch (Exception e){
            return "Item is not added. " + e.getMessage();
        }
    }

    @Transactional
    @Override
    public String updateItem(ItemDTO itemDTO, Long id) {
        // We create a new item using the DTO given
        Item newItem = itemRepo.findById(id);
        newItem.setName(itemDTO.getName());
        newItem.setPrice(itemDTO.getPrice());
        newItem.setStock(itemDTO.getStock());

        try{ // Save the new item aka updatw
            itemRepo.persist(newItem);
            return "Item is updated succesfully";
        }catch (Exception e){
            return "Item is not updated. " + e.getMessage();
        }
    }

    @Transactional
    @Override
    public String deleteItem(Long id) {
        Item tempItem = itemRepo.findById(id);
        try { // Delete the tempItem
            itemRepo.delete(tempItem);
            return "Item is deleted succesfully";
        }catch (Exception e){
            return "Item delete failed. " + e.getMessage();
        }
    }
}