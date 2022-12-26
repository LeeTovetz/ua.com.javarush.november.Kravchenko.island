package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 400, maxSatiety = 60, maxOnOneLocation = 20, possibleDistance = 4)
public class Horse extends Herbivore {

    @Override
    public String toString() {
        return "Horse";
    }
}