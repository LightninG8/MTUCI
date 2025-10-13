package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringConfig {
    @Bean
    public Weapon sword() {
        return new Sword();
    }
    @Bean
    public Weapon bow() {
        return new Bow();
    }
    @Bean
    public Weapon magicWand() {
        return new MagicWand();
    }
    @Bean
    public Armory armory() {
        return new Armory(List.of(sword(), bow(), magicWand()));
    }
}
