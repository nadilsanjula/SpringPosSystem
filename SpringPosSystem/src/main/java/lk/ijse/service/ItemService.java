package lk.ijse.service;

import lk.ijse.dto.ItemStatus;
import lk.ijse.dto.impl.Item;

import java.util.List;

public interface ItemService {
    void saveItem(Item itemDTO);
    List<Item> getAllItems();
    ItemStatus getItem(String itemId);
    void deleteItem(String itemID);
    void updateItem(String itemId,Item itemDTO);
}
