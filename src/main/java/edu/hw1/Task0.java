package edu.hw1;

import org.apache.logging.log4j.LogManager;

public class Task0 {

    private Task0() {}

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("uncommentedmain")
    public static void main(String[] args) {
        LOGGER.info("Привет, мир!");
    }
}
