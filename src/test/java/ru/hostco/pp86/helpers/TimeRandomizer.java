package ru.hostco.pp86.helpers;

import java.time.LocalTime;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeRandomizer {

    private static final int actualHour = LocalTime.now().getHour();
    private static final int actualMinute = LocalTime.now().getMinute();

    /**
     * Returns pseudo-random time from specified interval
     */
    public static Time randomTime(int start, int end) {
        int hour = randomHour(start, end);
        int minute = randomMinute(0, 60);
        return new Time(hour, minute);
    }

    /**
     * Returns pseudo-random hour from specified interval
     */
    public static int randomHour(int start, int end) {
        assertThat(end).as("Setting the end hour is FAILED").isBetween(0, 23);
        assertThat(start)
                .as("Setting the start hour is FAILED").isBetween(0, 23)
                .as("Start hour must be less than end hour").isLessThan(end);
        int range = end - start;
        int random = new Random().nextInt(range + 1);
        return start + random;
    }

    /**
     * Returns pseudo-random minute from specified interval
     */
    public static int randomMinute(int start, int end) {
        assertThat(end).as("Setting the end minute is FAILED").isBetween(0, 60);
        assertThat(start)
                .as("Setting the start minute is FAILED").isBetween(0, 60)
                .as("Start hour minute be less than end minute").isLessThan(end);
        int range = end - start;
        int random = new Random().nextInt(range + 1);
        return start + random;
    }
}
