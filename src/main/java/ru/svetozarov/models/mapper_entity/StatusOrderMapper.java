package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.StatusOrderEntity;
import ru.svetozarov.models.pojo.Status;

/**
 * Created by Evgenij on 21.03.2017.
 */
public class StatusOrderMapper {
    public static Status converterToStatusDriver(StatusOrderEntity statusOrderEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(StatusOrderEntity.class, Status.class).
                field("id", "id").
                field("name", "name").
                field("description", "description").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        Status status = mapperFacade.map(statusOrderEntity, Status.class);
        return status;
    }
    public static StatusOrderEntity converterToStatusDriverEntity(Status status){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Status.class, StatusOrderEntity.class).
                field("id", "id").
                field("name", "name").
                field("description", "description").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        StatusOrderEntity statusDriverEntity = mapperFacade.map(status, StatusOrderEntity.class);
        return statusDriverEntity;
    }
}
