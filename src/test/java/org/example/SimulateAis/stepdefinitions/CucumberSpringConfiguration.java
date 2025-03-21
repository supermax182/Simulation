package org.example.SimulateAis.stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.SimulateAis.SimulateAisApplication; // Убедись, что имя соответствует главному классу приложения

@CucumberContextConfiguration
@SpringBootTest(classes = SimulateAisApplication.class)
public class CucumberSpringConfiguration {
}
