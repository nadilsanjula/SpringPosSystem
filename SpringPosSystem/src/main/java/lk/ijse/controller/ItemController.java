package lk.ijse.controller;

import lk.ijse.customStatusCodes.ErrorStatus;
import lk.ijse.dto.ItemStatus;
import lk.ijse.dto.impl.Item;
import lk.ijse.exeption.DataPersistException;
import lk.ijse.exeption.ItemNotFoundException;
import lk.ijse.service.ItemService;
import lk.ijse.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody Item itemDTO){
        try{
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/{itemCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedItem(@PathVariable ("itemCode") String itemId){
        if(!RegexProcess.itemIdMatcher(itemId)){
            return new ErrorStatus(1,"Item code is not valid");
        }
        return itemService.getItem(itemId);
    }
    public List<Item> getAllItems(){
        return null;
    }
    @DeleteMapping(value = "{/itemCode}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemCode") String itemId){
        try{
            if(!RegexProcess.itemIdMatcher(itemId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(ItemNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "{/itemCode}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemCode") String itemId,@RequestBody Item updatedItemDTO){
        //validations
        try {
            if(!RegexProcess.itemIdMatcher(itemId) || updatedItemDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updatedItemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
