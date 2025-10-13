package com.example;

public class Bow implements Weapon {
    private String name;

    public Bow(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println(name + " выпускает стрелу!");
    }
}
