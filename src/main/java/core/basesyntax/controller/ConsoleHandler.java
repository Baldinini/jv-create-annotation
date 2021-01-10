package core.basesyntax.controller;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.lib.Inject;
import core.basesyntax.model.Bet;
import core.basesyntax.model.User;
import java.util.Scanner;

public class ConsoleHandler {
    @Inject
    private BetDao betDao;
    @Inject
    private UserDao userDao;

    public void handle() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("stop")) {
                return;
            }
            Bet bet = null;
            try {
                String[] betData = command.split(" ");
                int value = Integer.parseInt(betData[0]);
                double risk = Double.parseDouble(betData[1]);
                bet = new Bet(value, risk);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please, enter correct data");
            }
            if (bet != null) {
                betDao.add(bet);
                System.out.println(bet);
            }
        }
    }

    public void handleUser() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("stop")) {
                return;
            }
            User user = null;
            try {
                String[] betData = command.split(" ");
                String firstName = betData[0];
                String lastName = betData[1];
                int age = Integer.parseInt(betData[2]);
                user = new User(firstName, lastName, age);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please, enter correct data");
            }
            if (user != null) {
                userDao.add(user);
                System.out.println(user);
            }
        }
    }
}
