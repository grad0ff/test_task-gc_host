package ru.hostco.pp86.models;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class EntriesPojoModel {

    private Integer id;
    private String createDate;
    private Indicator indicator;
    private String value;

    @Data
    @Accessors(fluent = true)
    public static class Indicator {
        private Integer id;
        private String name;
        private String unit;
    }
}
