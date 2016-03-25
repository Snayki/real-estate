package com.real.estate.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Snayki on 22.03.2016.
 */
public enum City {
    NotSelected("Не вибрано"),
    Cherkasy("Черкаси");

    private String name;

    City(String name) {
        this.name = name;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static City create(JsonNode json) {
        return cityOf(json.asText());
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<City> getAll() {
        return Arrays.asList(Cherkasy);
    }

    public static City cityOf(String name) {
        for (City city : values()) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return NotSelected;
    }
}
