package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class TravelController {
    private final UserRepository userRepository;
    private final CountryTemperatureService countryTemperatureService;
    private final Logger logger = LoggerFactory.getLogger(TravelController.class);
    private final TravelRepository travelRepository;
    TravelController(UserRepository userRepository, TravelRepository travelRepository, CountryTemperatureService countryTemperatureService){
        this.userRepository = userRepository;
        this.travelRepository = travelRepository;
        this.countryTemperatureService = countryTemperatureService;
    }
    @PostMapping("/api/inscription")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request) {
        userRepository.save(request);
        return ResponseEntity.ok(userRepository.getRegisters());
    }

    @GetMapping("/api/travels")
    @ResponseBody
    public List<TravelRequest> getTravels(@RequestParam String userName) {
        List<RegisterRequest> user = userRepository.findByUserName(userName);
        List<TravelRequest> travels = travelRepository.getTravelsByUserName(user);
        logger.info(travels.toString());
        return travels;
    }
}
