package lk.ijse.dto.impl;

import lk.ijse.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements ItemStatus {
    private String itemCode;
    private String itemName;
    private int itemQuantity;
    private int itemPrice;
}
