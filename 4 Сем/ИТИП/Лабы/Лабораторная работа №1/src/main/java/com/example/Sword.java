package com.example;

public class Sword extends Weapon {
    public Sword(String name) {
        super(name);
    }

    @Override
    public void use() {
        System.out.println(getName() + " наносит рубящий удар!");
    }
}
