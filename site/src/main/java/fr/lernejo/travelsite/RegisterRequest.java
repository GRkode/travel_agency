package fr.lernejo.travelsite;

public record RegisterRequest(String userEmail, String userName, String userCountry, String weatherExpectation, int minimumTemperatureDistance) {
}
