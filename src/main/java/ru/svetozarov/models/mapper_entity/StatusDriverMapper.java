package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.StatusDriverEntity;
import ru.svetozarov.models.pojo.Status;

/**
 * Created by Evgenij on 20.03.2017.
 */
public class StatusDriverMapper {
    public static Status converterToStatusDriver(StatusDriverEntity statusDriverEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(StatusDriverEntity.class, Status.class).
                field("id", "id").
                field("name", "name").
                field("description", "description").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        Status status = mapperFacade.map(statusDriverEntity, Status.class);
        return status;
    }
}
