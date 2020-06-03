package machine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    Scanner scanner;
    State state;
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
        cm.map.put("1", new Coffee(250, 0, 16, 4)); // espresso
        cm.map.put("2", new Coffee(350, 75, 20, 7)); //latte
        cm.map.put("3", new Coffee(200, 100, 12, 6)); //cappuccino
        cm.state = State.CHOOSING_AN_ACTION;
        while (cm.state != State.EXIT) {
            cm.state.printTitle();
            cm.operate(cm.scanner.next());
        }
    }

    void operate(String string) {
        switch (state) {
            case CHOOSING_AN_ACTION:
                switch (string) {
                    case ("buy"):
                        System.out.println();
                        state = State.CHOOSING_A_COFFEE;
                        return;
                    case ("fill"):
                        System.out.println();
                        state = State.ADD_WATER;
                        return;
                    case ("take"):
                        System.out.printf("%nI gave you $%d%n%n", money);
                        money = 0;
                        state = State.CHOOSING_AN_ACTION;
                        return;
                    case ("remaining"):
                        System.out.printf("%nThe coffee machine has:%n" +
                                "%d of water%n" +
                                "%d of milk%n" +
                                "%d of coffee beans%n" +
                                "%d of disposable cups%n" +
                                "$%d of money%n%n", water, milk, beans, cups, money);
                        state = State.CHOOSING_AN_ACTION;
                        return;
                    case ("exit"):
                        state = State.EXIT;
                        return;
                    default:
                        return;
                }
            case CHOOSING_A_COFFEE:
                if (!"back".equals(string)) {
                    Coffee coffee = map.get(string);
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
                System.out.println();
                state = State.CHOOSING_AN_ACTION;
                return;
            case ADD_WATER:
                water += Integer.parseInt(string);
                state = State.ADD_MILK;
                return;
            case ADD_MILK:
                milk += Integer.parseInt(string);
                state = State.ADD_BEANS;
                return;
            case ADD_BEANS:
                beans += Integer.parseInt(string);
                state = State.ADD_CUPS;
                return;
            case ADD_CUPS:
                cups += Integer.parseInt(string);
                System.out.println();
                state = State.CHOOSING_AN_ACTION;
                return;

        }
    }
}

class Coffee {
    int water, milk, beans, money;

    public Coffee(int water, int milk, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
    }
}

enum State {
    CHOOSING_AN_ACTION("Write action (buy, fill, take, remaining, exit):"),
    CHOOSING_A_COFFEE("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
    EXIT(""),
    ADD_WATER("Write how many ml of water do you want to add:"),
    ADD_MILK("Write how many ml of milk do you want to add:"),
    ADD_BEANS("Write how many grams of coffee beans do you want to add:"),
    ADD_CUPS("Write how many disposable cups of coffee do you want to add:");

    String title;

    State(String title){
        this.title = title;
    }

    void printTitle(){
        System.out.println(title);
    }
}
