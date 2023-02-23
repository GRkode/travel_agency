package fr.lernejo.travelsite;

import fr.lernejo.prediction.TemperatureRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<TemperatureRequest> getTemperatures(@Query("country") String country);
}
