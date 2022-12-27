package ua.com.javarush.november.kravchenko.island.system;

import ua.com.javarush.november.kravchenko.island.Runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {
    }

    public static int getProperty(String parameter) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(Objects.requireNonNull(Runner.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(properties.getProperty(parameter));
    }
}
