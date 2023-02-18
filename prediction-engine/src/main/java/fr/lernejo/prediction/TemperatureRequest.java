package fr.lernejo.prediction;

import java.util.List;

public record TemperatureRequest(String country, List<Temperature> temperatures) {
}
