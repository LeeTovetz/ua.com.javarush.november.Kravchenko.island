package ua.com.javarush.november.kravchenko.island.exceptions;

public class MissingAnnotationException extends RuntimeException {
    public MissingAnnotationException(String message) {
        super(message);
    }
}