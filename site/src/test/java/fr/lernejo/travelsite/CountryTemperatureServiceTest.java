/*
package fr.lernejo.travelsite;

import fr.lernejo.prediction.Temperature;
import fr.lernejo.prediction.TemperatureRequest;
import okio.Timeout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryTemperatureServiceTest {

    @Test
    void getFilteredCountryTemperatures_ShouldReturnFilteredTemperatures_WhenCalledWithRequest() throws IOException {
        // Given
        PredictionEngineClient client = mock(PredictionEngineClient.class);
        CountryTemperatureService service = new CountryTemperatureService(client);
        RegisterRequest request = new RegisterRequest("user@example.com", "John", "France", "Sunny", 20);

        String country1 = "France";
        String country2 = "Spain";
        String country3 = "Italy";
        String country4 = "Germany";
        List<String> countries = Arrays.asList(country1, country2, country3, country4);
        when(service.getCountries()).thenReturn(countries.stream());

        double temperature1 = 25.0;
        double temperature2 = 15.0;
        double temperature3 = 18.0;
        double temperature4 = 22.0;
        List<Temperature> temperatures1 = Arrays.asList(new Temperature(null, temperature1));
        List<Temperature> temperatures2 = Arrays.asList(new Temperature(null, temperature2));
        List<Temperature> temperatures3 = Arrays.asList(new Temperature(null, temperature3));
        List<Temperature> temperatures4 = Arrays.asList(new Temperature(null, temperature4));
        TemperatureRequest request1 = new TemperatureRequest(country1, temperatures1);
        TemperatureRequest request2 = new TemperatureRequest(country2, temperatures2);
        TemperatureRequest request3 = new TemperatureRequest(country3, temperatures3);
        TemperatureRequest request4 = new TemperatureRequest(country4, temperatures4);
        when(client.getTemperatures(country1)).thenReturn(new MockCall<>(request1));
        when(client.getTemperatures(country2)).thenReturn(new MockCall<>(request2));
        when(client.getTemperatures(country3)).thenReturn(new MockCall<>(request3));
        when(client.getTemperatures(country4)).thenReturn(new MockCall<>(request4));

        // When
        List<TravelRequest> result = service.getFilteredCountryTemperatures(request);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(TravelRequest::country).containsExactly(country1, country4);
        assertThat(result).extracting(TravelRequest::temperature).containsExactly(temperature1, temperature4);
    }

    static class MockCall<T> implements retrofit2.Call<T> {

        private final T response;

        public MockCall(T response) {
            this.response = response;
        }

        @Override
        public retrofit2.Response<T> execute() throws IOException {
            return retrofit2.Response.success(response);
        }

        @Override
        public void enqueue(retrofit2.Callback<T> callback) {

        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public retrofit2.Call<T> clone() {
            return null;
        }

        @Override
        public okhttp3.Request request() {
            return null;
        }

        @Override
        public Timeout timeout() {
            return null;
        }

    }

}
*/
