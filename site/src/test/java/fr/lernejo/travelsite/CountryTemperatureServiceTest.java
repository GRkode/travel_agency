package fr.lernejo.travelsite;

import fr.lernejo.prediction.Temperature;
import fr.lernejo.prediction.TemperatureRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CountryTemperatureServiceTest {
    /*@Mock
    private PredictionEngineClient client;
    private CountryTemperatureService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CountryTemperatureService(client);
    }

    @Test
    void getTravels_withNoUsers_shouldReturnEmptyList() throws IOException {
        List<RegisterRequest> users = new ArrayList<>();
        List<TravelRequest> result = service.getTravels(users);
        assertEquals(0, result.size());
        //verifyZeroInteractions(client);
    }

    @Test
    void getTravels_withUsers_shouldReturnCorrectTravels() throws IOException {
        List<RegisterRequest> users = List.of(
            new RegisterRequest("user1@example.com", "User 1", "France", "COLD", 5),
            new RegisterRequest("user2@example.com", "User 2", "Spain", "HOT", 20)
        );
        List<String> countries = List.of("France", "Spain");
        List<TravelRequest> countryWeathers = List.of(
            new TravelRequest("France", 0.0),
            new TravelRequest("Spain", 30.0)
        );
        when(client.getTemperatures("France")).thenReturn(mockCall(10.0, 5.0));
        when(client.getTemperatures("Spain")).thenReturn(mockCall(40.0, 20.0));
        when(client.getTemperatures("Unknown")).thenReturn(mockCall(Double.NaN, Double.NaN));
        when(service.getCountries()).thenReturn(countries);
        when(service.getCountriesWeather(countries)).thenReturn(countryWeathers);

        List<TravelRequest> result = service.getTravels(users);

        assertEquals(1, result.size());
        TravelRequest travel = result.get(0);
        assertEquals("France", travel.country());
        assertEquals(10.0, travel.temperature());

        verify(client, times(1)).getTemperatures("France");
        verify(client, times(1)).getTemperatures("Spain");
        verify(client, never()).getTemperatures("Unknown");
    }

    private Call<TemperatureRequest> mockCall(double temp1, double temp2) throws IOException {
        List<Temperature> temperatures = List.of(
            new Temperature("2023-02-21", temp1),
            new Temperature("2023-02-22", temp2)
        );
        TemperatureRequest response = new TemperatureRequest("country", temperatures);
        Response<TemperatureRequest> retrofitResponse = Response.success(response);
        Call<TemperatureRequest> call = mock(Call.class);
        when(call.execute()).thenReturn(retrofitResponse);
        return call;
    }*/
}
