package fr.lernejo.travelsite;

import fr.lernejo.prediction.Temperature;
import fr.lernejo.prediction.TemperatureRequest;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryTemperatureService {

    private final PredictionEngineClient client;

    public CountryTemperatureService(PredictionEngineClient client) {
        this.client = client;
    }

    public List<TravelRequest> getTravels(List<RegisterRequest> users) throws IOException {
        List<String> countries = getCountries();
        List<TravelRequest> countryWeathers = getCountriesWeather(countries);

        return countryWeathers.stream()
            .map(countryWeather -> new TravelRequest(countryWeather.country(), countryWeather.temperature()))
            .filter(destination -> {
                double temperature = destination.temperature();
                for (RegisterRequest user : users) {
                    if (temperature < user.minimumTemperatureDistance()) {
                        return false;
                    }
                    if (!destination.country().equals(user.userCountry())) {
                        continue;
                    }
                    if (destination.temperature() < user.minimumTemperatureDistance() + Double.parseDouble(user.weatherExpectation())) {
                        return false;
                    }
                }
                return true;
            })
            .collect(Collectors.toList());
    }

    private List<String> getCountries() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return content.lines().collect(Collectors.toList());
    }

    private List<TravelRequest> getCountriesWeather(List<String> countries) throws IOException {
        List<TravelRequest> countryWeathers = new ArrayList<>();

        for (String country : countries) {
            Response<TemperatureRequest> response = client.getTemperatures(country).execute();

            if (response.isSuccessful()) {
                TemperatureRequest prediction = response.body();
                List<Temperature> temperatures = prediction.temperatures();
                double averageTemperature = temperatures.stream().mapToDouble(Temperature::temperature).average().orElse(Double.NaN);
                countryWeathers.add(new TravelRequest(country, averageTemperature));
            } else {
                countryWeathers.add(new TravelRequest(country, Double.NaN));
            }
        }

        return countryWeathers;
    }
}
