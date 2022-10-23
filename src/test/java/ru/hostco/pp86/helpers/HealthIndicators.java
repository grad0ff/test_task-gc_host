package ru.hostco.pp86.helpers;

public enum HealthIndicators {

    TEMPERATURE("Температура"),
    WEIGHT("Вес"),
    PRESSURE("Давление"),
    SUGAR_LEVEL("Уровень сахара в крови"),
    PULSE("Пульс"),
    MOOD("Настроение"),
    ALCOHOL("Алкоголь в крови"),
    AMBIVALENCE("Амбивалентность"),
    HEALTH_STATUS("Общее состояние здоровья"),
    SKIN_CONDITION("Состояние кожных покровов");

    private final String indicatorName;

    HealthIndicators(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String text() {
        return indicatorName;
    }
}
