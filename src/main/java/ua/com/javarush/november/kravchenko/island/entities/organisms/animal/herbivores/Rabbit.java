package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 2, maxSatiety = 0.45, maxOnOneLocation = 150, possibleDistance = 2)
public class Rabbit extends Herbivore {

    @Override
    public String toString() {
        return "Rabbit";
    }
}