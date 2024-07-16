package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.Item;

import java.util.List;

@ApplicationScoped
public class ItemRepo implements PanacheRepository<Item> {

    public List<Item> getItemList() {
        return listAll();
    }

    public Item findByID(Long id){
        return findById(id);
    }

    @Transactional
    public String addItem(Item item) {
        try {
            persist(item);
            return "Item is added succesfully";
        }catch (Exception e){
            return "Item is not added. " + e.getMessage();
        }
    }

    @Transactional
    public String updateItem(Item item, Long id){
        Item newItem = findById(id);
        newItem.setName(item.getName());
        newItem.setPrice(item.getPrice());
        newItem.setStock(item.getStock());

        try{
            persist(newItem);
            return "Item is updated succesfully";
        }catch (Exception e){
            return "Item is not updated. " + e.getMessage();
        }
    }

    @Transactional
    public String deleteItem(Long id) {
        Item tempItem = findByID(id);
        try {
            delete(tempItem);
            return "Item is deleted succesfully";
        }catch (Exception e){
            return "Item delete failed. " + e.getMessage();
        }
    }
}
