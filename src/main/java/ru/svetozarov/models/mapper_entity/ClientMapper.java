package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.AutoEntity;
import ru.svetozarov.models.entity.ClientEntity;
import ru.svetozarov.models.pojo.Client;

/**
 * Created by Evgenij on 21.03.2017.
 */
public class ClientMapper {
    public static Client converterToClient(ClientEntity clientEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(ClientEntity.class, Client.class).
                field("id", "id").
                field("name", "name").
                field("sex", "sex").
                field("phone", "phone").
                field("email", "email").
                field("login", "login").
                field("password", "password").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        Client client = mapperFacade.map(clientEntity, Client.class);
        return client;
    }

    public static ClientEntity converterToClientEntity(Client client){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Client.class, ClientEntity.class).
                field("id", "id").
                field("name", "name").
                field("sex", "sex").
                field("phone", "phone").
                field("email", "email").
                field("login", "login").
                field("password", "password").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        ClientEntity clientEntity = mapperFacade.map(client, ClientEntity.class);
        return clientEntity;
    }
}
