package machine;

import java.util.Scanner;

public class CoffeeMachine {
    Scanner scanner = new Scanner(System.in);
    int water = 400;
    int milk = 540;
    int beans = 120;
    int cups = 9;
    int money = 550;

    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine();
        cm.print();
        System.out.println();
        switch (cm.ask()){
            case("buy"): cm.buy(); break;
            case("fill"): cm.fill(); break;
            case("take"): cm.take(); break;
        }
        System.out.println();
        cm.print();

    }

    void print(){
        System.out.printf("The coffee machine has:%n" +
                "%d of water%n" +
                "%d of milk%n" +
                "%d of coffee beans%n" +
                "%d of disposable cups%n" +
                "%d of money%n", water, milk, beans, cups, money);
    }

    String ask(){
        System.out.println("Write action (buy, fill, take):");
        return scanner.next();
    }

    void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    void fill() {
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
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        switch (scanner.nextInt()){
            case (1):
                water -= 250;
                beans -= 16;
                cups--;
                money += 4;
                break;
            case (2):
                water -= 350;
                milk -= 75;
                beans -= 20;
                cups--;
                money += 7;
                break;
            case (3):
                water -= 200;
                milk -= 100;
                beans -= 12;
                cups--;
                money += 6;
                break;
        }
    }
}
