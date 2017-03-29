package ru.svetozarov.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.svetozarov.models.entity.OrderEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by Evgenij on 23.03.2017.
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findByEntytiStatusIdNotInAndEntityClientId(Collection<Integer> status, int id);
    OrderEntity findById(int id);
    List<OrderEntity> findByEntytiStatusIdInAndEntityDriverIdIn(Collection<Integer> status,
                                                                Collection<Integer> id_driver);

    List<OrderEntity> findByEntytiStatusIdAndEntityDriverIdIsNull(int status);
    //List<OrderEntity> findByEntityStatusIdAndEntityDriverIdIn(int id_status, Collection<Integer> )


}
