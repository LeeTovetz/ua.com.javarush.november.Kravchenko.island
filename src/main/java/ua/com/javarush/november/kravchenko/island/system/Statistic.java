package ua.com.javarush.november.kravchenko.island.system;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.AnimalTypes;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.herbivores.Herbivore;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators.Predator;
import ua.com.javarush.november.kravchenko.island.entities.island.Island;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;

import java.util.ArrayList;
import java.util.List;

public class Statistic implements Runnable {
    private final Island island;
    private final Location[][] locations;

    public Statistic(Island island) {
        this.island = island;
        this.locations = island.getLocations();
    }

    @Override
    public void run() {
        printInfo();
    }

    public void printInfo() {
        Logger.log("==================================================");
        Logger.logSameLine("Count of predators: " + island.getCountPredators());
        Logger.logSameLine(", Count of herbivores: " + island.getCountHerbivores());
        Logger.log(", Total: " + island.getCountAnimals());
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Location location = locations[i][j];
                Logger.log("Location [" + location.getCoordinates().getX() + ", " + location.getCoordinates().getY() + "]");
                Logger.logSameLine("Predators [ ");
                for (AnimalTypes animalType : AnimalTypes.values()) {
                    List<Predator> predators = new ArrayList<>(location.getPredators());
                    int countPredators = (int) predators.stream().filter(p -> p.toString().equalsIgnoreCase(animalType.toString())).count();
                    if (countPredators > 0) {
                        System.out.print(animalType + ":" + countPredators + " ");
                    }
                }
                Logger.log(" ]");
                Logger.logSameLine("Herbivores [ ");
                for (AnimalTypes animalType : AnimalTypes.values()) {
                    List<Herbivore> herbivores = new ArrayList<>(location.getHerbivores());
                    int countHerbivores = (int) herbivores.stream().filter(p -> p.toString().equalsIgnoreCase(animalType.toString())).count();
                    if (countHerbivores > 0) {
                        System.out.print(animalType + ":" + countHerbivores + " ");
                    }
                }
                Logger.log(" ]");
                Logger.log("Plants [ " + location.getPlants().size() + " ]");
            }
        }
    }
}
