package ru.svetozarov.models.repository;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.entity.OrderEntity;

import java.util.List;

/**
 * Created by Evgenij on 23.03.2017.
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Integer>{
    List<OrderEntity> findByEntityClientId(int id);
}
