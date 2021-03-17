package com.assignment;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {



    ArrayList<Weapon> shopItems = new ArrayList<>();

    public Shop(){

        shopItems.add(new Weapon("Sword of Azeroth", 20));
        shopItems.add(new Weapon("Thunderfury", 30));
        shopItems.add(new Weapon("Mort'Regal heavenly mace", 50));
        shopItems.add(new Weapon("bloody stick", 10));

    }

    public ArrayList<Weapon> getItems() {
        return shopItems;
    }
}
