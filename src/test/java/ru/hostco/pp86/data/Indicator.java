package ru.hostco.pp86.data;

import java.util.Random;

public enum Indicator {

    AMBIVALENCE(1, "Амбивалентность", "%"),
    HEALTH_STATUS(2, "Общее состояние здоровья", null),
    SKIN_CONDITION(3, "Состояние кожных покровов", null),
    ALCOHOL(4, "Алкоголь в крови", "промиль"),
    MOOD(5, "Настроение", null),
    TEMPERATURE(62, "Температура", "°С"),
    WEIGHT(81, "Вес", "кг"),
    PRESSURE(82, "Давление", null),
    SUGAR_LEVEL(83, "Уровень сахара в крови", "ммоль/л"),
    PULSE(84, "Пульс", "уд./мин.");

    private final String text;
    private final String unit;
    private final int id;

    Indicator(int id, String indicatorName, String unit) {
        this.text = indicatorName;
        this.unit = unit;
        this.id = id;
    }

    public static Indicator random() {
        Indicator[] indicators = Indicator.values();
        int random = new Random().nextInt(indicators.length);
        for (Indicator indicator : indicators) {
            if (indicator.ordinal() == random) return indicator;
        }
        return null;
    }

    public String text() {
        return text;
    }

    public String unit() {
        return unit;
    }

    public Integer id() {
        return id;
    }
}
