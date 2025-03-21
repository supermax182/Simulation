package org.example.SimulateAis.stepdefinitions;

import io.cucumber.java.ParameterType;

public class ParamTypes {

    @ParameterType("true|false")
    public boolean bool(String value) {
        return Boolean.parseBoolean(value);
    }
}
