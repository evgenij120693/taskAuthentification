package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.AutoEntity;
import ru.svetozarov.models.entity.DriverEntity;
import ru.svetozarov.models.pojo.Auto;
import ru.svetozarov.models.pojo.Driver;

/**
 * Created by Evgenij on 20.03.2017.
 */
public class AutoMapper {
    public static Auto converterToDriver(AutoEntity autoEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(AutoEntity.class, Auto.class).
                field("id", "id").
                field("marka", "marka").
                field("regNumber", "regNumber").
                field("color", "color").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        Auto auto = mapperFacade.map(autoEntity, Auto.class);
        return auto;
    }
}
