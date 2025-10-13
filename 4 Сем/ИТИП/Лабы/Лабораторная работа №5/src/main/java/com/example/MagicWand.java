package com.example;

import org.springframework.stereotype.Component;

@Component
public class MagicWand implements Weapon {
    private String name;
    public MagicWand() {
        this.name = "Палочка Гарри Поттера";
    }
    @Override
    public String getName() { return name; }
    @Override
    public void use() {
        System.out.println(name + " создаёт магическую вспышку!");
    }
}
