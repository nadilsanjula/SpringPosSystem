package lk.ijse.dao;

import lk.ijse.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ItemDao extends JpaRepository<ItemEntity,String> {
}
