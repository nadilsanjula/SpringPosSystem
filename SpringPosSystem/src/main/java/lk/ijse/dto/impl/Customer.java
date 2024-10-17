package lk.ijse.dto.impl;

import lk.ijse.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer implements CustomerStatus {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
}
