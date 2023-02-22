package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelController {
    private final UserRepository userRepository;
    private final CountryTemperatureService countryTemperatureService;
    private final TravelRepository travelRepository;
    TravelController(UserRepository userRepository, TravelRepository travelRepository, CountryTemperatureService countryTemperatureService){
        this.userRepository = userRepository;
        this.travelRepository = travelRepository;
        this.countryTemperatureService = countryTemperatureService;
    }
    @PostMapping("/api/inscription")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request) {
        userRepository.save(request);
        return ResponseEntity.ok("Inscription réussie !");
    }

    @GetMapping("/api/travels")
    @ResponseBody
    public List<TravelRequest> getTravels(@RequestParam String userName) {
        List<RegisterRequest> user = userRepository.findByUserName(userName);
        List<TravelRequest> travels = travelRepository.getTravelsByUserName(user);
        return travels;
    }
}
