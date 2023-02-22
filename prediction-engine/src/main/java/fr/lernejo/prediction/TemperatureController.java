package fr.lernejo.prediction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TemperatureController {
    private final TemperatureService temperatureService;

    TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/api/temperature")
    public ResponseEntity<TemperatureRequest> getTemperature(@RequestParam String country) {
        try {
            double temp1 = temperatureService.getTemperature(country);
            double temp2 = temperatureService.getTemperature(country);
            Temperature t1 = new Temperature(LocalDate.now().minusDays(1), temp1);
            Temperature t2 = new Temperature(LocalDate.now(), temp2);
            List<Temperature> temperatures = List.of(t1, t2);
            TemperatureRequest response = new TemperatureRequest(country, temperatures);
            return ResponseEntity.ok(response);
        } catch (UnknownCountryException e) {
            return ResponseEntity.status(417).build();
        }
    }
}
