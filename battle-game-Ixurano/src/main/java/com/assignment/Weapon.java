package com.assignment;

import java.io.Serializable;

public class Weapon implements Serializable {

    private final String name;
    private final double price;

    //skapar funktionen weapon med olika atribut
    public Weapon(String name, double price) {
        this.name=name;
        this.price=price;
    }
    public String getName() {
        return name;
    }

    //@Override //overshadow price från lektionen ÄNDRA DETTA SEN SÅ DE PASSAR TILL SPELET
    double getPrice() {
        return price;
    }
}
