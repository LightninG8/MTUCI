package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Armory {
    private final List<Weapon> weapons;

    @Autowired
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
