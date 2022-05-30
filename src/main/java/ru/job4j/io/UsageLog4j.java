package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        byte age = 33;
        int height = 175;
        boolean isMale = true;
        float nalog = 4834.44f;
        double weight = 75.4463874;
        char houseBuilding = 'a';
        long salary = 1234567895;
        LOG.debug("User info name : {}, age : {}, height : {}, "
                + "is male : {}, nalog : {}, weight : {}, building : {}, salary : {}",
                name, age, height, isMale, nalog, weight, houseBuilding, salary);
    }
}