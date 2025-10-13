package com.example;

import org.springframework.stereotype.Component;

@Component
public class Sword implements Weapon {
    private String name;
    public Sword() {
        this.name = "Экскалибур";
    }
    @Override
    public String getName() { return name; }
    @Override
    public void use() {
        System.out.println(name + " наносит рубящий удар!");
    }
}
