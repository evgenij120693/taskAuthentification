package ru.svetozarov.models.repository;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.entity.ClientEntity;

import java.util.List;

/**
 * Created by Evgenij on 23.03.2017.
 */
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    List<ClientEntity> findByLoginAndPassword(String login, String password);
}
