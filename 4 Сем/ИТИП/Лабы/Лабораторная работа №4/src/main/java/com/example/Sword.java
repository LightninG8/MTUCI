package com.example;

public class Sword implements Weapon {
    private String name;

    public Sword(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println(name + " наносит рубящий удар!");
    }
}