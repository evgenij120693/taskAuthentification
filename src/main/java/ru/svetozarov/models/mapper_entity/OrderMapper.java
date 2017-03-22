package ru.svetozarov.models.mapper_entity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.svetozarov.models.entity.OrderEntity;
import ru.svetozarov.models.pojo.Order;

/**
 * Created by Evgenij on 21.03.2017.
 */
public class OrderMapper {
    public static Order converterToOrder(OrderEntity orderEntity){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(OrderEntity.class, Order.class).
                field("id", "id").
                field("entityClient", "entityClient").
                field("dateRegistration", "dateRegistration").
                field("punktA", "punktA").
                field("punktB", "punktB").
                field("price", "price").
                field("entityDriver", "entityDriver").
                field("dateStart", "dateStart").
                field("dateEnd", "dateEnd").
                field("entytiStatus", "entytiStatus").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        System.out.println(orderEntity.getDateStart() + " order start");
        Order order = mapperFacade.map(orderEntity, Order.class);
        order.setEntityClient(ClientMapper.converterToClient(orderEntity.getEntityClient()));
        order.setEntityDriver(DriverMapper.converterToDriver(orderEntity.getEntityDriver()));
        order.setEntytiStatus(StatusOrderMapper.converterToStatusOrder(orderEntity.getEntytiStatus()));
        return order;
    }
    public static OrderEntity converterToOrderEntity(Order order){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Order.class, OrderEntity.class).
                field("id", "id").
                field("entityClient", "entityClient").
                field("dateRegistration", "dateRegistration").
                field("punktA", "punktA").
                field("punktB", "punktB").
                field("price", "price").
                field("entityDriver", "entityDriver").
                field("dateStart", "dateStart").
                field("dateEnd", "dateEnd").
                field("entytiStatus", "entytiStatus").
                toClassMap();
        MapperFacade mapperFacade = factory.getMapperFacade();
        OrderEntity orderEntity  = mapperFacade.map(order, OrderEntity.class);
        orderEntity.setEntityClient(ClientMapper.converterToClientEntity(order.getEntityClient()));
        orderEntity.setEntityDriver(DriverMapper.converterToDriverEntity(order.getEntityDriver()));
        orderEntity.setEntytiStatus(StatusOrderMapper.converterToStatusOrderEntity(order.getEntytiStatus()));
        return orderEntity;
    }
}
