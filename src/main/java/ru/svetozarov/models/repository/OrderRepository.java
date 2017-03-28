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
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    //@Query("select b from  OrderEntity b where b.id_client = :id_client and b.id_status=:id_status")
    List<OrderEntity> findByEntytiStatusIdNotInAndEntityClientId(Collection<Integer> status, int id);

    //OrderEntity findByName(@Param("name") String name);
    //
}
