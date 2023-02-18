package fr.lernejo.travelsite;
import org.springframework.web.bind.annotation.RequestParam;
import fr.lernejo.prediction.TemperatureRequest;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PredictionEngineClient {
    @GET("/api/temperature?country={country}")
    Call<TemperatureRequest> getTemperatures(@RequestParam String country);
}
