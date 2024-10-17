package lk.ijse.util;

import lk.ijse.dto.impl.Customer;
import lk.ijse.dto.impl.Item;
import lk.ijse.entity.impl.CustomerEntity;
import lk.ijse.entity.impl.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //for customer mapping
    public CustomerEntity toCustomerEntity(Customer customerDTO){
        return modelMapper.map(customerDTO,CustomerEntity.class);
    }

    public Customer toCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity,Customer.class);
    }

    public List<Customer> asCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities, new TypeToken<List<Customer>>() {}.getType());
    }
    //for item mapping
    public Item toItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, Item.class);
    }
    public ItemEntity toItemEntity(Item itemDTO){
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public List<Item> asItemDTOList(List<ItemEntity> itemEntityList){
        return modelMapper.map(itemEntityList, new TypeToken<List<Item>>() {}.getType());
    }
}

