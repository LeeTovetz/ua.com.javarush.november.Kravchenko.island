# Animal Life Simulation Island<br>

**This is the final project of the second "Java Core" module at JavaRush University.**  <br>
Created by Serge Kravchenko (group of students "November").<br>
##
**Task**<br>
Program a model of an island with variable parameters, consisting of an array of locations (for example, 100x20 cells).<br> 
The locations will be filled with vegetation and animals.<br> 

**Animals can:**<br>
- eat plants and/or other animals (if their location has suitable food),
- move (to neighboring locations),
- breed (if there is a pair in their location),
- starve to death or be eaten.

**Requirements**<br>
Create a hierarchy of classes: 
- Predator (Wolf, Boar, Fox, Bear, Eagle), 
- Herbivores (Horse, Deer, Rabbit, Mouse, Goat, Sheep, Boar, Buffalo, Duck, Caterpillar), 
- Plants.

The animal must have methods:<br>
- to eat, 
- reproduce, 
- choose the direction of movement.<br>

In herbivore and predator classes, you can implement the method to eat. <br>
But note, there is a herbivorous duck that eats a caterpillar.<br>
In specific classes of this or that species, you can refine all methods to fit the peculiarities of the animal.<br>
There must be at least 10 species of herbivore and 5 species of carnivore.<br>
Multithreading.<br>
Statistics on the state of the island on each cycle.<br>

**Running the program**<br>
The program is started in the console.<br> 
The size of the island can be controlled in the file island.properties.<br>
