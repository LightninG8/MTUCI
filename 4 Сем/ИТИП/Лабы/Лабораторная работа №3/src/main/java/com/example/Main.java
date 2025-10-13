package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Weapon sword = context.getBean("sword", Weapon.class);
        Weapon bow = context.getBean("bow", Weapon.class);
        Weapon magicWand = context.getBean("magicWand", Weapon.class);

        sword.use();
        bow.use();
        magicWand.use();

        context.close();
    }
}
