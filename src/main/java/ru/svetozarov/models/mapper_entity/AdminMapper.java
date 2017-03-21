package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.AdminEntity;
import ru.svetozarov.models.pojo.Admin;

/**
 * Created by Evgenij on 21.03.2017.
 */
public class AdminMapper {
    public static Admin converterToAdmin(AdminEntity adminEntity) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(AdminEntity.class, Admin.class).
                field("id", "id").
                field("name", "name").
                field("login", "login").
                field("password", "password").
                field("email", "email").
                field("sendEmailFlag", "sendEmailFlag").
                toClassMap();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Admin admin = mapperFacade.map(adminEntity, Admin.class);
        return  admin;
    }
    public static AdminEntity converterToAdminEntity(Admin admin) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Admin.class, AdminEntity.class).
                field("id", "id").
                field("name", "name").
                field("login", "login").
                field("password", "password").
                field("email", "email").
                field("sendEmailFlag", "sendEmailFlag").
                toClassMap();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        AdminEntity adminEntity = mapperFacade.map(admin, AdminEntity.class);
        return  adminEntity;
    }
}
