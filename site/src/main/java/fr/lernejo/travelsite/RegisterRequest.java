package fr.lernejo.travelsite;

public record RegisterRequest(String userEmail, String userName, String userCountry, WeatherExpectation weatherExpectation, int minimumTemperatureDistance) {
}
