package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RestController
public class TravelController {
    private final UserRepository userRepository;
    private final CountryTemperatureService countryTemperatureService;
    private final Logger logger = LoggerFactory.getLogger(TravelController.class);

    TravelController(UserRepository userRepository, TravelRepository travelRepository, CountryTemperatureService countryTemperatureService){
        this.userRepository = userRepository;
        this.countryTemperatureService = countryTemperatureService;
    }
    @PostMapping("/api/inscription")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request) {
        userRepository.save(request);
        return ResponseEntity.ok(userRepository.getRegisters());
    }

    @GetMapping("/api/travels")
    @ResponseBody
    public List<TravelRequest> getTravels(@RequestParam String userName) throws IOException {
        List<RegisterRequest> users = userRepository.findByUserName(userName);
        if (users.isEmpty()) {
            return List.of();
        }
        return countryTemperatureService.getTravels(users);
    }
}
