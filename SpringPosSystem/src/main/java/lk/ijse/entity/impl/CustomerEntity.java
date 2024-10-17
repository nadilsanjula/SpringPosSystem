package lk.ijse.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String customerId;
    private String customerName;
    @Column(unique = true)
    private String customerEmail;
    private String customerAddress;
    @Column(unique = true)
    private String customerPhone;
    /*@OneToMany(mappedBy = "customer")
    private List<ItemEntity> items;*/
}
