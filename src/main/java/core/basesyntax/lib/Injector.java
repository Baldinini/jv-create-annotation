package core.basesyntax.lib;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.exception.AnnotationException;
import core.basesyntax.factory.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {

    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor constructor = clazz.getDeclaredConstructor();
        Object instance = constructor.newInstance();

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                Object value = null;
                if (field.getType().equals(BetDao.class)) {
                    value = Factory.getBetDao();
                } else if (field.getType().equals(UserDao.class)) {
                    value = Factory.getUserDao();
                }
                if (value != null && !value.getClass().isAnnotationPresent(Dao.class)) {
                    throw new AnnotationException("Your class doesn't have annotation @Dao");
                }
                field.set(instance, value);
            }
        }
        return instance;
    }
}
