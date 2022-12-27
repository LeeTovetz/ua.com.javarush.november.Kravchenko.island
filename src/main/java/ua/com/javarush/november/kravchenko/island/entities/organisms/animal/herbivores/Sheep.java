package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 70, maxSatiety = 15, maxOnOneLocation = 140, possibleDistance = 3)
public class Sheep extends Herbivore {

    @Override
    public String toString() {
        return "Sheep";
    }
}