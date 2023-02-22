package fr.lernejo.travelsite;

import fr.lernejo.prediction.Temperature;
import fr.lernejo.prediction.TemperatureRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CountryTemperatureService {
    private final PredictionEngineClient predictionEngineClient;
    private final
    List<TravelRequest> countryTemperatures = new ArrayList<>();

    public CountryTemperatureService(PredictionEngineClient predictionEngineClient) {
        this.predictionEngineClient = predictionEngineClient;
    }

    public List<TravelRequest> getFilteredCountryTemperatures(RegisterRequest request) throws IOException {
        Stream<String> countries = getCountries();

        getCountryTemperature(countries, request);

        return countryTemperatures.stream()
            .filter(travelRequest -> travelRequest.temperature() >= request.minimumTemperatureDistance())
            .collect(Collectors.toList());
    }

    Stream<String> getCountries() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return content.lines();
    }

    void getCountryTemperature(Stream<String> countries, RegisterRequest request) {
        countries.forEach(countryName -> {
            try {
                TemperatureRequest temperatureRequest = predictionEngineClient.getTemperatures(countryName).execute().body();
                List<Temperature> temperatures = temperatureRequest.temperatures();
                double averageTemperature = temperatures.stream().mapToDouble(Temperature::temperature).average().orElse(Double.NaN);
                if (averageTemperature >= request.minimumTemperatureDistance() && temperatureRequest.country().equals(countryName)) {
                    TravelRequest countryTemperature = new TravelRequest(countryName, averageTemperature);
                    countryTemperatures.add(countryTemperature);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to get temperatures for country: " + countryName, e);
            }
        });
    }
}
