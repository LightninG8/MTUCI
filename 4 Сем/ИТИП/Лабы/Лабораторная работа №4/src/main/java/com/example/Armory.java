package com.example;

import java.util.List;

public class Armory {
    private List<Weapon> weapons;

    public Armory(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void displayWeapons() {
        weapons.forEach(weapon -> {
            System.out.println("Оружие: " + weapon.getName());
            weapon.use();
        });
    }
}