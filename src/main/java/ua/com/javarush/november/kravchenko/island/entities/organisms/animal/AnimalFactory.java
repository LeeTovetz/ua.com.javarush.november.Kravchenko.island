package ua.com.javarush.november.kravchenko.island.entities.organisms.animal;

public interface AnimalFactory {
    Animal createAnimal(AnimalTypes types);
}
