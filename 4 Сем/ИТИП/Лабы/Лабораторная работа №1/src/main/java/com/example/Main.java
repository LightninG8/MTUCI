package com.example;

public class Main {
    public static void main(String[] args) {
        Weapon sword = new Sword("Экскалибур");
        Weapon bow = new Bow("Лук Робина");
        Weapon magicWand = new MagicWand("Палочка Гарри Поттера");

        sword.use();
        bow.use();
        magicWand.use();
    }
}
