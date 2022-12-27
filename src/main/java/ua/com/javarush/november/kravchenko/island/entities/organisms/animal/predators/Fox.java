package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Animal;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Caterpillar;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Duck;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Mouse;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Rabbit;

import java.util.Map;

@Characteristics(weight = 8, maxSatiety = 2, maxOnOneLocation = 30, possibleDistance = 2)
public class Fox extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Caterpillar.class, 40);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Fox";
    }
}