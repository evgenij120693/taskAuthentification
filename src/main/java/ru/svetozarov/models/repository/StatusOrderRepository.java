package ru.svetozarov.models.repository;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.entity.StatusOrderEntity;

/**
 * Created by Evgenij on 23.03.2017.
 */
public interface StatusOrderRepository extends CrudRepository<StatusOrderEntity, Integer> {

}
