package com.assignment;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    // Kod för spelarens Inventory av vapen i början av spelet

    ArrayList<Weapon> items = new ArrayList<>();
//skapar en arraylist av nuvarande vapen och liknande.
    public Inventory(){
        items.add(new Weapon("Stick", 0));
        items.add(new Weapon("Big rock", 0));
        items.add(new Weapon("knuckle dusters", 0));
    }
    public ArrayList<Weapon> getItems() {
        return items;
    }

    //funktion som lägger till vapen ifall spelaren köper nya i shoppen.
    public void addItem(Weapon weapon) {

        items.add(weapon);

    }

}


