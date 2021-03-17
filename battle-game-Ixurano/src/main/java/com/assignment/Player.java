package com.assignment;

import java.io.Serializable;
import java.util.Random;

abstract class Player implements Serializable {

    //åtkomst datatyp varibelnamn ="värde"
    private String name;
    String currency = "EUR";
    private double health = 100.00;
    private double money = 100.00;
    Inventory inventory = new Inventory();
    Weapon latestPurchase;
    static int playerCounter = 0;

    //konstruktor metod
    public Player(String name) {
        this(name, 100);
    }

    public Player(String name, double money) {
        setName(name);
        setMoney(money);
        playerCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    //resetar npc health så spelaren kan forsätta spelet.
    public void newHealth(double health){this.health=100; }

    public void setMoney(double money) {
        setMoney(money, this.currency);
    }
   public void setMoney(double money, String currency) {
        this.money = money;
        this.currency = currency;
    }

    public double getMoney() {
        return money;
    }
//funktion när spelaren köper något så tar de priset samt reset.
    public String buyThing(Weapon weapon){
        money = money - weapon.getPrice();
        latestPurchase = weapon;
        getInventory().addItem(latestPurchase);
        return String.format("You bought %s for %.2f", weapon.getName(), weapon.getPrice());
    }

    public void addLoot(){
        double lootMoney = new Random().nextInt(60) + 1;
        money = money + lootMoney;
        System.out.println("You looted: " + lootMoney);
    }

    public double turnHumanDmg() {
        double npcAttack = new Random().nextInt(25) + 1;
        setHealth(health - npcAttack);
        return npcAttack;
    }
    public double turnEnemyDmg() {
        double npcAttack = new Random().nextInt(23) + 1;
        setHealth(health - npcAttack);
        return npcAttack;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public boolean isDead() {
        return health <= 0;
    }
}
