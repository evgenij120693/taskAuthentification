package ru.svetozarov.common.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Evgenij on 20.03.2017.
 */
public enum Factory {
    INSTANCEOF;
    private static  final  EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("ENTITY");

    public static EntityManagerFactory getFACTORY() {
        return FACTORY;
    }
}
