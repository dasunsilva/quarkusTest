package org.dasun.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dasun.dto.ItemDTO;
import org.dasun.dto.mappers.ItemDTOMapper;
import org.dasun.exceptions.DatabaseException;
import org.dasun.exceptions.InvalidLongException;
import org.dasun.model.Bill;
import org.dasun.model.Item;
import org.dasun.repo.ItemRepo;
import org.dasun.service.ItemService;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {

    @Inject
    ItemRepo itemRepo;

    @Inject
    ItemDTOMapper itemDTOMapper;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemDTO getItem(Long id) throws InvalidLongException {
        // Get item using ID
        Item tempItem = itemRepo.findById(id);
        return itemDTOMapper.mapItemDTO(tempItem);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String addItem(ItemDTO itemDTO) throws DatabaseException {
        // Create a item using DTO given
        Item tempItem = itemDTOMapper.mapDTOItem(itemDTO);

        try { // Save item
            itemRepo.persist(tempItem);
            return "Item is added succesfully";
        }catch (Exception e){
            throw new DatabaseException("Error while adding new item");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String updateItem(ItemDTO itemDTO, Long id) throws DatabaseException {
        // We create a new item using the DTO given
        Item newItem = itemRepo.findById(id);
        newItem.setName(itemDTO.getName());
        newItem.setPrice(itemDTO.getPrice());
        newItem.setStock(itemDTO.getStock());

        try{ // Save the new item aka updatw
            itemRepo.persist(newItem);
            return "Item is updated succesfully";
        }catch (Exception e){
            throw new DatabaseException("Error while updating new item");

        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public String deleteItem(Long id) throws DatabaseException {
        Item tempItem = itemRepo.findById(id);
        try { // Delete the tempItem
            itemRepo.delete(tempItem);
            return "Item is deleted succesfully";
        }catch (Exception e){
            throw new DatabaseException("Error while updating new item");

        }
    }
}
