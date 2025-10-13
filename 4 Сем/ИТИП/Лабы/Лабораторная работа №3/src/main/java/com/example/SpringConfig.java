package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public Weapon sword() {
        return new Sword("Экскалибур");
    }

    @Bean
    public Weapon bow() {
        return new Bow("Лук Робина");
    }

    @Bean
    public Weapon magicWand() {
        return new MagicWand("Палочка Гарри Поттера");
    }
}
