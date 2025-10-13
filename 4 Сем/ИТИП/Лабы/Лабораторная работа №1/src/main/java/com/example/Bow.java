package com.example;

public class Bow extends Weapon {
    public Bow(String name) {
        super(name);
    }

    @Override
    public void use() {
        System.out.println(getName() + " выпускает стрелу!");
    }
}
