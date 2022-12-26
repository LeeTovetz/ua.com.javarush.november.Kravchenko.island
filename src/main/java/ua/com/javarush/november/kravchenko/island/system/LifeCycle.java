package ua.com.javarush.november.kravchenko.island.system;

import ua.com.javarush.november.kravchenko.island.entities.island.Island;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;

public class LifeCycle implements Runnable {
    Location location;
    Island island;

    public LifeCycle(Location location, Island island) {
        this.location = location;
        this.island = island;
    }

    @Override
    public void run() {
        location.getLock().lock();
        try {
            location.eating();
            location.breeding();
            location.moving(island.getLocations());
            location.dying();
        } finally {
            location.getLock().unlock();
        }
    }
}
