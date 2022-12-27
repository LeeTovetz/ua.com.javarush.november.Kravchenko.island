package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 700, maxSatiety = 100, maxOnOneLocation = 10, possibleDistance = 3)
public class Buffalo extends Herbivore {

    @Override
    public String toString() {
        return "Buffalo";
    }
}