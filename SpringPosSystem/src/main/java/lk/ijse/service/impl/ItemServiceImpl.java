package lk.ijse.service.impl;

import lk.ijse.customStatusCodes.ErrorStatus;
import lk.ijse.dao.ItemDao;
import lk.ijse.dto.ItemStatus;
import lk.ijse.dto.impl.Item;
import lk.ijse.entity.impl.ItemEntity;
import lk.ijse.exeption.DataPersistException;
import lk.ijse.exeption.ItemNotFoundException;
import lk.ijse.service.ItemService;
import lk.ijse.util.AppUtil;
import lk.ijse.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;
    /*private static List<NoteDTO> noteDTOList = new ArrayList<>();

    NoteServiceImpl(){
        noteDTOList.add(new NoteDTO())
    }*/
    @Override
    public void saveItem(Item itemDTO) {
        itemDTO.setItemCode(AppUtil.generateItemId());
        ItemEntity savedNote = itemDao.save(mapping.toItemEntity(itemDTO));
        if(savedNote == null){
            throw new DataPersistException("Item not saved.");
        }
    }

    @Override
    public List<Item> getAllItems() {
        return mapping.asItemDTOList(itemDao.findAll());
    }

    @Override
    public ItemStatus getItem(String itemId) {
        if(itemDao.existsById(itemId)){
            var selectedItem = itemDao.getReferenceById(itemId);
            return mapping.toItemDTO(selectedItem);
        }
        return new ErrorStatus(2, "Selected item not found.");
    }

    @Override
    public void deleteItem(String itemID) {
        Optional<ItemEntity> foundNote = itemDao.findById(itemID);
        if(!foundNote.isPresent()){
            throw new ItemNotFoundException("Item not found.");
        }else{
            itemDao.deleteById(itemID);
        }
    }

    @Override
    public void updateItem(String noteId, Item itemDTO) {
        Optional<ItemEntity> findNote = itemDao.findById(noteId);
        if(!findNote.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else {
            findNote.get().setItemName(itemDTO.getItemName());
            findNote.get().setItemQuantity(itemDTO.getItemQuantity());
            findNote.get().setItemPrice(itemDTO.getItemPrice());
        }
    }
}
