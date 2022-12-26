package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Animal;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

import java.util.Map;

@Characteristics(weight = 400, maxSatiety = 50, maxOnOneLocation = 50, possibleDistance = 2)
public class Boar extends Herbivore {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Mouse.class, 50, Caterpillar.class, 80);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Boar";
    }
}