package com.example;

import org.springframework.stereotype.Component;

@Component
public class Bow implements Weapon {
    private String name;
    public Bow() {
        this.name = "Лук Робина";
    }
    @Override
    public String getName() { return name; }
    @Override
    public void use() {
        System.out.println(name + " выпускает стрелу!");
    }
}
