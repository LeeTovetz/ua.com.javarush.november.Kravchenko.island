package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Animal;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Duck;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Mouse;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Rabbit;

import java.util.Map;

@Characteristics(weight = 15, maxSatiety = 3, maxOnOneLocation = 30, possibleDistance = 1)
public class Boa extends Predator {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Fox.class, 15, Rabbit.class, 20, Mouse.class, 40, Duck.class, 10);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Boa";
    }
}