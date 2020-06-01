package machine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    Scanner scanner;
    Map<String, Coffee> map;
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine(400, 540, 120, 9, 550);
        cm.map = new HashMap<>();
        cm.map.put("espresso", new Coffee(250, 0, 16, 4));
        cm.map.put("latte", new Coffee(350, 75, 20, 7));
        cm.map.put("cappuccino", new Coffee(200, 100, 12, 6));
        while (true) {
            switch (cm.ask()) {
                case ("buy"):
                    cm.buy();
                    break;
                case ("fill"):
                    cm.fill();
                    break;
                case ("take"):
                    cm.take();
                    break;
                case ("remaining"):
                    cm.print();
                    break;
                case ("exit"):
                    return;
            }
            System.out.println();
        }
    }

    void print() {
        System.out.printf("%nThe coffee machine has:%n" +
                "%d of water%n" +
                "%d of milk%n" +
                "%d of coffee beans%n" +
                "%d of disposable cups%n" +
                "$%d of money%n", water, milk, beans, cups, money);
    }

    String ask() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        return scanner.next();
    }

    void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    void fill() {
        System.out.println();
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }

    void buy() {
        while (true) {
            System.out.println();
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            switch (scanner.next()) {
                case ("1"):
                    make(map.get("espresso"));
                    return;
                case ("2"):
                    make(map.get("latte"));
                    return;
                case ("3"):
                    make(map.get("cappuccino"));
                    return;
                case ("back"):
                    return;
            }
        }
    }

    void make(Coffee coffee) {
        if (water < coffee.water) System.out.println("Sorry, not enough water!");
        else if (milk < coffee.milk) System.out.println("Sorry, not enough milk!");
        else if (beans < coffee.beans) System.out.println("Sorry, not enough coffee beans!");
        else if (cups == 0) System.out.println("Sorry, not enough disposable cups!");
        else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups--;
            money += coffee.money;
        }
    }

    static class Coffee {
        int water, milk, beans, money;

        public Coffee(int water, int milk, int beans, int money) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.money = money;
        }
    }
}
