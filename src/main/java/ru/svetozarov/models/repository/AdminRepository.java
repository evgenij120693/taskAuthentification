package ru.svetozarov.models.repository;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.entity.AdminEntity;

import java.util.List;

/**
 * Created by Evgenij on 23.03.2017.
 */
public interface AdminRepository extends CrudRepository<AdminEntity, Integer>{
      List<AdminEntity> findByLogin(String login);
    List<AdminEntity> findByLoginAndPassword(String login, String password);
}
