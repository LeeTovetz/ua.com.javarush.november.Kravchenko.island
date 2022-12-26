package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Animal;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Duck;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Mouse;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Rabbit;

import java.util.Map;

@Characteristics(weight = 6, maxSatiety = 1, maxOnOneLocation = 20, possibleDistance = 3)
public class Eagle extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Fox.class, 10, Rabbit.class, 90, Mouse.class, 90, Duck.class, 80);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Eagle";
    }
}