package lk.ijse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.customStatusCodes.ErrorStatus;
import lk.ijse.dao.CustomerDao;
import lk.ijse.dto.CustomerStatus;
import lk.ijse.dto.impl.Customer;
import lk.ijse.entity.impl.CustomerEntity;
import lk.ijse.exeption.CustomerNotFoundException;
import lk.ijse.exeption.DataPersistException;
import lk.ijse.service.CustomerService;
import lk.ijse.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(Customer customerDTO) {
        /*UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
        mapping.touserDTO(savedUser);*/
        CustomerEntity savedCustomer = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if(savedCustomer == null){
            throw new DataPersistException("User not saved.");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerDao.findAll();
        return mapping.asCustomerDTOList(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        if(customerDao.existsById(customerId)){
            CustomerEntity selectedCustomer = customerDao.getReferenceById(customerId);
            return mapping.toCustomerDTO(selectedCustomer);
        }else{
            return new ErrorStatus(2, "Customer with id "+ customerId + "not found.");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> existedUser = customerDao.findById(customerId);
        if(!existedUser.isPresent()){
            throw new CustomerNotFoundException("User with id "+customerId+" not found");
        }else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerID, Customer customerDTO) {
        Optional<CustomerEntity> tmpUser = customerDao.findById(customerID);
        if(tmpUser.isPresent()){
            tmpUser.get().setCustomerName(customerDTO.getCustomerName());
            tmpUser.get().setCustomerEmail(customerDTO.getCustomerEmail());
            tmpUser.get().setCustomerAddress(customerDTO.getCustomerAddress());
            tmpUser.get().setCustomerPhone(customerDTO.getCustomerPhone());
        }
    }
}
