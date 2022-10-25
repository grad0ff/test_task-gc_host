package ru.hostco.pp86.helpers;

import com.github.javafaker.Faker;

public class Indicators {

    private static Faker faker = new Faker();

    public static String randomValue(ru.hostco.pp86.data.Indicators indicator) {
        switch (indicator) {
            case AMBIVALENCE:
                return String.valueOf(faker.number().numberBetween(1, 101));
            case HEALTH_STATUS:
            case SKIN_CONDITION:
                return faker.medical().symptoms();
            case ALCOHOL:
            case SUGAR_LEVEL:
                return String.valueOf(faker.number().randomDouble(4, 0, 1));
            case MOOD:
                return faker.medical().diseaseName();
            case TEMPERATURE:
                return String.valueOf(faker.number().randomDouble(2, 36, 41));
            case WEIGHT:
                return String.valueOf(faker.number().randomDouble(1, 1, 150));
            case PRESSURE:
                int maxPressure = faker.number().numberBetween(100, 150);
                int minPressure = faker.number().numberBetween(50, 100);
                return String.format("[\"%d\", \"%d\"]", maxPressure, minPressure);
            case PULSE:
                return String.valueOf(faker.number().numberBetween(100, 150));
        }
        return null;
    }
}
