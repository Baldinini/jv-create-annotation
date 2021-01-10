package core.basesyntax;

import core.basesyntax.controller.ConsoleHandler;
import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.factory.Factory;
import core.basesyntax.lib.Injector;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConsoleHandler handler = (ConsoleHandler) Injector.getInstance(ConsoleHandler.class);
        System.out.println("Enter value and risk for your bet");
        handler.handle();

        ConsoleHandler user = (ConsoleHandler) Injector.getInstance(ConsoleHandler.class);
        System.out.println("Enter your name, last name and age");
        user.handleUser();

        BetDao betDao = Factory.getBetDao();
        System.out.println("All bets: " + betDao.getAll());

        UserDao userDao = Factory.getUserDao();
        System.out.println("All users: " + userDao.getAll());
    }
}
