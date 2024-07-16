package org.dasun.service;

import org.dasun.dto.ItemDTO;
import org.dasun.dto.UserDTO;

import java.util.List;

public interface ItemService {
    public List<ItemDTO> getAllItems();
    public ItemDTO getItem(Long id);
    public String addItem(ItemDTO userDTO);
    public String updateItem(ItemDTO userDTO, Long id);
    public String deleteItem(Long id);
}
