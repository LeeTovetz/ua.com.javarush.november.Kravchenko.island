package ua.com.javarush.november.kravchenko.island.entities.organisms.animal.predators;

import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.AnimalFactory;
import ua.com.javarush.november.kravchenko.island.entities.organisms.animal.AnimalTypes;

public class PredatorFactory implements AnimalFactory {
    @Override
    public Predator createAnimal(AnimalTypes type) {
        Predator predator;
        switch (type) {
            case BEAR -> predator = new Bear();
            case BOA -> predator = new Boa();
            case EAGLE -> predator = new Eagle();
            case FOX -> predator = new Fox();
            case WOLF -> predator = new Wolf();
            default -> predator = null;
        }
        return predator;
    }
}
