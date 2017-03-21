package ru.svetozarov.models.repository;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.entity.DriverEntity;

import java.util.List;

/**
 * Created by Evgenij on 21.03.2017.
 */
public interface DriverRepository extends CrudRepository<DriverEntity, Integer>{
    List<DriverEntity> findByLogin(String login);
    List<DriverEntity> findByLoginAndPassword(String login, String password);
}
