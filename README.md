# Animal Life Simulation Island<br>

**This is the final project of the second "Java Core" module at JavaRush University.**  <br>
Created by Serge Kravchenko (group of students "November").<br>
##
**Task**
Program a model of an island with variable parameters, consisting of an array of locations (for example, 100x20 cells). The locations will be filled with vegetation and animals. 

**Animals can:**
- eat plants and/or other animals (if their location has suitable food),
- move (to neighboring locations),
- breed (if there is a pair in their location),
- starve to death or be eaten.

**Requirements**
Create a hierarchy of classes: 
- Predator (Wolf, Boar, Fox, Bear, Eagle), 
- Herbivores (Horse, Deer, Rabbit, Mouse, Goat, Sheep, Boar, Buffalo, Duck, Caterpillar), 
- Plants.

**The animal must have methods:** 
- to eat, 
- reproduce, 
- choose the direction of movement.
In herbivore and predator classes, you can implement the method to eat. But note, there is a herbivorous duck that eats a caterpillar.
In specific classes of this or that species, you can refine all methods to fit the peculiarities of the animal.
There must be at least 10 species of herbivore and 5 species of carnivore.
Multithreading
Statistics on the state of the island on each cycle

**Running the program**
The program is started in the console. The size of the island can be controlled in the file island.properties.
