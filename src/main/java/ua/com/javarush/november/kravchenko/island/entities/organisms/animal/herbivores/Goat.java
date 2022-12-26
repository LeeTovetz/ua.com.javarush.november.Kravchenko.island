package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 60, maxSatiety = 10, maxOnOneLocation = 140, possibleDistance = 3)
public class Goat extends Herbivore {

    @Override
    public String toString() {
        return "Goat";
    }
}
