package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Characteristics;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;

@Characteristics(weight = 0.01, maxSatiety = 0, maxOnOneLocation = 1000)
public class Caterpillar extends Herbivore {

    @Override
    public boolean move(Location currentLocation, Location[][] locations) {
        return false;
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }
}

