package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;

@Characteristics(weight = 300, maxSatiety = 50, maxOnOneLocation = 20, possibleDistance = 4)
public class Deer extends Herbivore {

    @Override
    public String toString() {
        return "Deer";
    }
}
