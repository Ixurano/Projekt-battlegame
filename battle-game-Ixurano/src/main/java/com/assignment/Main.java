package com.assignment;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //koden kollar om det finns en duglig sparfil eller ej
        Human player;
        String playerName;
        Scanner console = new Scanner(System.in);
        if(FileUtils.loadObject("player.save")!= null){
            player =(Human) FileUtils.loadObject("player.save");
        }else{
            System.out.println("Your name:");
            playerName = console.nextLine();
            player = new Human(playerName);
        }
        String attack;
        Shop shop = new Shop();
        Enemy npc = new Enemy("Local Karen");

        FileUtils.writeTextFile(player.getName(), "hello.txt");
        String readStr = FileUtils.readTextFile("hello.txt");
        System.out.println("Welcome back: "+ readStr);

            System.out.println("Welcome to the migthy dungeon Werket");
            boolean avsluta = false;
            while (!avsluta) {
            System.out.println("Do you want to enter the dungeon adventurer, visit the murky merchant or leave with the tail between your leg?");
            npc.newHealth(100);
            player.newHealth(100);

            System.out.println("1. Enter the dungeon\n2. Visit the merchant\n3. End the game");
            int val = console.nextInt();
            //själva "dungeon" funktionen där spelaren möter fiender med olika vals alternativ.
            if (val == 1) {
                while (!npc.isDead() && !player.isDead()) {
                    System.out.println(player.getName() + " health is " + player.getHealth());
                    System.out.println(npc.getName() + " health is " + npc.getHealth());
                    //spelaren väljer sitt vapen från inventory
                    System.out.println("Choose you weapon: ");
                    boolean wepChoice = false;
                    while (!wepChoice) {
                        Weapon weaponList;
                        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
                            weaponList = player.getInventory().getItems().get(i);
                            System.out.format("%d - %s \n",
                                    i + 1,
                                    weaponList.getName());
                        }
                        String choosenWeapon = console.next();
                        if (choosenWeapon.matches("\\d+")) {
                            int itemChoice = Integer.parseInt(choosenWeapon) - 1;

                            if (itemChoice < player.getInventory().getItems().size()) {
                                wepChoice=true;
                            } else {
                                System.out.println("Wrong input");
                            }
                        }
                    }
                    System.out.println("\nDo you want to attack? Y/N \n");
                    attack = console.next().toUpperCase();
                    if (attack.equals("Y")) {
                        System.out.println(player.getName() + " attacks Karen for = " + npc.turnHumanDmg());

                    } else if (attack.equals("N")) {
                        System.out.println("You ran away from the scary Karen");
                        System.out.println("She throws her brand new pair of crocks at you");
                        System.out.println("It critical hits");
                        break;
                    }
                    System.out.println(npc.getName() + " attacks you for = " + player.turnEnemyDmg());

                    if (npc.getHealth() <= 0) {
                        player.addLoot();
                        System.out.println(npc.getName() + " is dead");
                        System.out.println(player.getName() + " wins");
                    } else if (player.getHealth() <= 0) {
                        System.out.println(player.getName() + " is dead");
                        System.out.println(npc.getName() + " wins and calls the manager");
                    }
                }
            }
            //butikens logik som sparar de du köper i player Inventory
            if (val == 2) {
                Weapon currentThing;
                boolean shopping = false;
                while (!shopping) {
                    for (int i = 0; i < shop.getItems().size(); i++) {
                        currentThing = shop.getItems().get(i);
                        System.out.format("%d - %s %.2f $\n",
                                i + 1,
                                currentThing.getName(),
                                currentThing.getPrice());
                    }
                    System.out.format("You have %.2f $\n", player.getMoney());
                    System.out.format("Shiny shiny weapons for sell, choose [1-%d] write [q] to leave the shop ", shop.getItems().size());

                    String shopAction = console.next();

                    if (shopAction.matches("\\d+")) {
                        int itemIdx = Integer.parseInt(shopAction) - 1;

                        if (itemIdx < shop.getItems().size()) {

                            Weapon currentWeapon = shop.getItems().get(itemIdx);

                            System.out.println(player.buyThing(currentWeapon) + "\n");
                        } else {
                            System.out.println("Wrong input");
                        }
                    } else {
                        System.out.println("Return when you want traveler\n");
                        shopping = true;
                    }
                }
            }
            //Avslutar spelet samt sparar den nuvarande spelaren
            if (val == 3) {
                FileUtils.savedObject(player, "player.save"); //gitignore
                avsluta = true;
            }
        }
    }
}

