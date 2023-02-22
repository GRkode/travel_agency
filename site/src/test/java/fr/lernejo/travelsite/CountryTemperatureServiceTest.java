package fr.lernejo.travelsite;

import fr.lernejo.prediction.Temperature;
import fr.lernejo.prediction.TemperatureRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static sun.rmi.transport.TransportConstants.Call;

class CountryTemperatureServiceTest {

    @Mock
    private PredictionEngineClient predictionEngineClient;

    private CountryTemperatureService countryTemperatureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryTemperatureService = new CountryTemperatureService(predictionEngineClient);
    }

    @Test
    void testGetTemperaturesForCountry() throws IOException {
        // Mock PredictionEngineClient
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        List<String> countries = new ArrayList<>(List.of(content.split("\n")));
        List<Temperature> temperatures = new ArrayList<>();
        for (String country : countries) {
            double temp1 = Math.random() * 40 - 20;
            double temp2 = Math.random() * 40 - 20;
            Temperature t1 = new Temperature(LocalDate.now().minusDays(1), temp1);
            Temperature t2 = new Temperature(LocalDate.now(), temp2);
            temperatures.add(t1);
            temperatures.add(t2);
            TemperatureRequest temperatureRequest = new TemperatureRequest(country, temperatures);
            byte TemperatureRequest;
            when(predictionEngineClient.getTemperatures(country)).thenReturn(Call<TemperatureRequest>);
        }

        // Test CountryTemperatureService
        List<TravelRequest> expected = new ArrayList<>();
        TravelRequest travelRequest;
        for (String country : countries) {
            double avgTemperature = (temperatures.get(0).temperature() + temperatures.get(1).temperature()) / 2;
            if (avgTemperature >= -10 && avgTemperature <= 30) {
                expected.add(new TravelRequest(country, avgTemperature));
            }
        }
        List<TravelRequest> actual = countryTemperatureService.getFilteredCountryTemperatures();

        assertEquals(expected, actual);
    }
}


