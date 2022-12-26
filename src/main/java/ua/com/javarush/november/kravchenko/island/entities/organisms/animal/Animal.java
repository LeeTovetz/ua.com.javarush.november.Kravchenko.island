package ua.com.javarush.november.kravchenko.island.entities.organisms.animal;

import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.november.kravchenko.island.exceptions.MissingAnnotationException;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators.Predator;
import ua.com.javarush.november.kravchenko.island.entities.island.Direction;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;
import ua.com.javarush.november.kravchenko.island.entities.organisms.plant.Plant;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Herbivore;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@SuppressWarnings("unchecked")
public abstract class Animal {
    public static final int COUNT_TRIES_TO_MOVE = 4;
    private double satiety;
    private Gender gender;
    private boolean isBreed;

    protected Animal() {
        this.satiety = getMaxSatiety() / 2;
        this.gender = getRandomGender();
        this.isBreed = false;
    }

    public double getSatiety() {
        return this.satiety;
    }

    public abstract boolean breed(Location location);

    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return Collections.emptyMap();
    }

    public void eat(List<?> objects, Location location) {
        location.getLock().lock();
        try {
            if (!objects.isEmpty()) {
                if (objects.get(0) instanceof Animal) {
                    eatAnimal((List<? extends Animal>) objects);
                } else if (objects.get(0) instanceof Plant) {
                    eatPlant((List<Plant>) objects);
                }
            }
        } finally {
            location.getLock().unlock();
        }
    }

    public boolean move(Location currentLocation, Location[][] locations) {
        currentLocation.getLock().lock();
        try {
            Location newLocation = null;
            int tries = 0;
            while (tries < COUNT_TRIES_TO_MOVE) {
                newLocation = getNewLocation(currentLocation, locations);
                if (newLocation != null) {
                    break;
                }
                tries++;
            }
            if (newLocation != null && newLocation != currentLocation) {
                newLocation.addAnimal(this);
                return true;

            } else {
                return false;
            }
        } finally {
            currentLocation.getLock().unlock();
        }
    }

    public boolean die(Location location) {
        location.getLock().lock();
        try {
            this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
            return this.getSatiety() == 0;
        } finally {
            location.getLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSatiety());
    }

    protected Gender getRandomGender() {
        return Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)];
    }

    protected double getMaxSatiety() {
        return this.getCharacteristics().maxSatiety();
    }

    protected void eatPlant(List<Plant> plants) {
        Iterator<Plant> iterator = plants.iterator();
        while (iterator.hasNext()) {
            Plant plant = iterator.next();
            if (this.getSatiety() < this.getMaxSatiety()) {
                this.setSatiety(Math.min(this.getSatiety() + plant.getWeight(), this.getMaxSatiety()));
                iterator.remove();
                return;
            }
        }
    }

    private double getWeight() {
        return this.getCharacteristics().weight();
    }

    private int getMaxOnOneLocation() {
        return this.getCharacteristics().maxOnOneLocation();
    }

    private int getPossibleDistance() {
        return this.getCharacteristics().possibleDistance();
    }

    private Characteristics getCharacteristics() {
        if (!this.getClass().isAnnotationPresent(Characteristics.class)) {
            throw new MissingAnnotationException("Missing annotation " + Characteristics.class + " for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(Characteristics.class);
    }

    private Location getNewLocation(Location currentLocation, Location[][] locations) {
        int oldCoordinateX = currentLocation.getCoordinates().getX();
        int oldCoordinateY = currentLocation.getCoordinates().getY();
        int lengthIsland = locations.length;
        int widthIsland = locations[0].length;
        Direction direction = getRandomDirection();
        int steps = getRandomSteps(direction, lengthIsland, widthIsland);
        int newCoordinateX = getNewCoordinateX(direction, oldCoordinateX, steps);
        int newCoordinateY = getNewCoordinateY(direction, oldCoordinateY, steps);
        if (!isValidCoordinates(newCoordinateX, newCoordinateY, lengthIsland, widthIsland)) {
            return null;
        }
        Location newLocation = locations[newCoordinateX][newCoordinateY];
        if (!isLocationFree(newLocation)) {
            return null;
        }
        return newLocation;
    }

    private int getNewCoordinateX(Direction direction, int oldCoordinateX, int steps) {
        int newCoordinateX = oldCoordinateX;
        if (direction == Direction.UP) {
            newCoordinateX = oldCoordinateX - steps;
        } else if (direction == Direction.DOWN) {
            newCoordinateX = oldCoordinateX + steps;
        }
        return newCoordinateX;
    }

    private int getNewCoordinateY(Direction direction, int oldCoordinateY, int steps) {
        int newCoordinateY = oldCoordinateY;
        if (direction == Direction.LEFT) {
            newCoordinateY = oldCoordinateY - steps;
        } else if (direction == Direction.RIGHT) {
            newCoordinateY = oldCoordinateY + steps;
        }
        return newCoordinateY;
    }

    private boolean isLocationFree(Location location) {
        if (this instanceof Herbivore) {
            List<Herbivore> herbivores = location.getHerbivores();
            int countHerbivoreOnNewLocation = (int) herbivores.stream().filter(this::equals).count();
            return countHerbivoreOnNewLocation < getMaxOnOneLocation();
        } else if (this instanceof Predator) {
            List<Predator> predators = location.getPredators();
            int countPredatorOnNewLocation = (int) predators.stream().filter(this::equals).count();
            return countPredatorOnNewLocation < getMaxOnOneLocation();
        } else {
            return false;
        }
    }

    private boolean isValidCoordinates(int newCoordinateX, int newCoordinateY, int lengthIsland, int widthIsland) {
        return newCoordinateX <= lengthIsland - 1 && newCoordinateY <= widthIsland - 1 && newCoordinateX >= 0 && newCoordinateY >= 0;
    }

    private Direction getRandomDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    private int getRandomSteps(Direction direction, int lengthIsland, int widthIsland) {
        int steps = 0;
        int possibleDistance = getPossibleDistance();
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            steps = Math.min(lengthIsland - 1, ThreadLocalRandom.current().nextInt(0, possibleDistance));
        } else if (direction == Direction.UP || direction == Direction.DOWN) {
            steps = Math.min(widthIsland - 1, ThreadLocalRandom.current().nextInt(0, possibleDistance));
        }
        return steps;
    }

    private void eatAnimal(List<? extends Animal> animals) {
        Iterator<? extends Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            Integer chanceToEat = this.getChanceToEat().get(animal.getClass());
            if (chanceToEat != null) {
                int random = ThreadLocalRandom.current().nextInt(1, 100);
                if (chanceToEat >= random && this.getSatiety() < this.getMaxSatiety()) {
                    this.setSatiety(Math.min(this.getSatiety() + animal.getWeight(), this.getMaxSatiety()));
                    iterator.remove();
                    return;
                }
            }
        }
    }
}

