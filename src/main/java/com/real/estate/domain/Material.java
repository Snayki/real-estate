package com.real.estate.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Snayki on 24.03.2016.
 */
public enum Material {
    Brick("Ц", "Цегляний"),
    Panel("П", "Панельний"),
    Monolith("М", "Моноліт"),
    Unknown("", "Невідомо");

    private String code;
    private String name;

    Material(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Material create(JsonNode json) {
        for (Material material: values()) {
            if (material.getName().equals(json.asText())) {
                return material;
            }
        }
        return Unknown;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Material> getAll() {
        return Arrays.asList(Brick, Panel, Monolith);
    }

    public static Material materialOf(String code) {
        for (Material material: values()) {
            if (material.getCode().equals(code)) {
                return material;
            }
        }
        return Unknown;
    }

}
