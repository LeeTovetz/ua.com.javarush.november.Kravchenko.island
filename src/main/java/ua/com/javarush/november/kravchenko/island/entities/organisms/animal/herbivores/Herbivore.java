package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Animal;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.Gender;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;

import java.util.List;

public abstract class Herbivore extends Animal {

    @Override
    public boolean breed(Location location) {
        location.getLock().lock();
        try {
            List<Herbivore> herbivoresFemale = location.getHerbivores().stream().filter(herbivore ->
                    herbivore.getGender() == Gender.FEMALE && !herbivore.isBreed()).toList();
            if (herbivoresFemale.isEmpty()) {
                return false;
            }
            for (int i = 0; i < herbivoresFemale.size(); i++) {
                Herbivore herbivoreFemale = herbivoresFemale.get(i);
                if (this.getGender() == Gender.MALE && !this.isBreed() && this.equals(herbivoreFemale)) {
                    this.setBreed(true);
                    this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                    herbivoreFemale.setBreed(true);
                    herbivoreFemale.setSatiety(Math.max(0, herbivoreFemale.getSatiety() - herbivoreFemale.getMaxSatiety() / 10));
                    return true;
                }
            }
            return false;
        } finally {
            location.getLock().unlock();
        }
    }
}

