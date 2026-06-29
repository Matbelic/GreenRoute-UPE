package br.upe.greenroute.controller;

public class BaseController {
    protected boolean validDouble (String value) {
        return (value.matches("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$"));
    }
    protected String isDouble (String value, String message) {
        if (!validDouble(value)) {
            return message;
        }
        return null;
    }
    protected boolean validInt (String value) {
        return (value.matches("^[+-]?\\d+$"));
    }
    protected String isInt (String value, String message) {
        if (!validInt(value)) {
            return message;
        }
        return null;
    }
    protected String isAnyBlank(String... values) {
        for (String value : values) {
            if (value == null || value.isBlank()) {
                return "Nenhum dado pode estar vazio!";
            }
        }
        return null;
    }
}
