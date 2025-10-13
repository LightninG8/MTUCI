package com.example;

public class MagicWand implements Weapon {
    private String name;

    public MagicWand(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println(name + " создаёт магическую вспышку!");
    }
}