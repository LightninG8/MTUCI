package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // ApplicationContext context = new AnnotationConfigApplicationContext("com.example");
        // Armory armory = context.getBean(Armory.class);
        // armory.displayWeapons();

        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // Armory armory = context.getBean(Armory.class);
        // armory.displayWeapons();

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Armory armory = context.getBean(Armory.class);
        armory.displayWeapons();
    }
}
