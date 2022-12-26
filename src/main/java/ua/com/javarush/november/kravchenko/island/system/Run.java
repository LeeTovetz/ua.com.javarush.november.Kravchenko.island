package ua.com.javarush.november.kravchenko.island.system;

import ua.com.javarush.november.kravchenko.island.entities.island.Island;
import ua.com.javarush.november.kravchenko.island.entities.island.Location;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Run {
    public static void RunApp() {
        int length = PropertiesReader.getProperty("length");
        int width = PropertiesReader.getProperty("width");

        Logger.log("Game Start. Island size: " + length + " X " + width);

        Island island = new Island(length, width);
        island.initialize();

        ScheduledExecutorService executorStat = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorLifeCycle = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorPlantGrowth = Executors.newScheduledThreadPool(1);

        executorStat.scheduleWithFixedDelay(new Statistic(island), 1, 1, TimeUnit.SECONDS);

        for (int i = 0; i < island.getLocations().length; i++) {
            for (int j = 0; j < island.getLocations()[i].length; j++) {
                Location location = island.getLocations()[i][j];
                executorLifeCycle.scheduleWithFixedDelay(new LifeCycle(location, island), 2, 1, TimeUnit.SECONDS);
                executorPlantGrowth.scheduleWithFixedDelay(new PlantGrowth(location), 3, 3, TimeUnit.SECONDS);
            }
        }

        while (true) {
            Delay.sleep(4000);
            if (island.getCountAnimals() == 0) {
                break;
            }
        }
        executorLifeCycle.shutdown();
        executorStat.shutdown();
        executorPlantGrowth.shutdown();
        Logger.log("==================================================");
        Logger.log("GAME OVER");
    }
}
