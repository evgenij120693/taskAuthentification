package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.DriverEntity;
import ru.svetozarov.models.pojo.Driver;

/**
 * Created by Evgenij on 20.03.2017.
 */
public class DriverMapper {
    public static Driver converterToDriver(DriverEntity driverEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(DriverEntity.class, Driver.class).
                field("id", "id").
                field("lastName", "lastName").
                field("firstName", "firstName").
                field("phoneNumber", "phoneNumber").
                field("login", "login").
                field("password", "password").
                field("rating", "rating").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        Driver driver = mapperFacade.map(driverEntity, Driver.class);
        driver.setEntryAuto(AutoMapper.converterToDriver(driverEntity.getEntryAuto()));
        driver.setEntryStatus(StatusDriverMapper.converterToStatusDriver(driverEntity.getEntryStatus()));
        return driver;
    }
}
