package lk.ijse.service;

import lk.ijse.dto.CustomerStatus;
import lk.ijse.dto.impl.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customerDTO);
    List<Customer> getAllCustomers();
    CustomerStatus getCustomer(String customerDTO);
    void deleteCustomer(String customerId);
    void updateCustomer(String userID,Customer customerDTO);
}
