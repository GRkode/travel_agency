package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravelRepository {
    private final List<TravelRequest> travels = new ArrayList<>();

    void addTravel(TravelRequest travelRequest){
        travels.add(travelRequest);
    }

    List<TravelRequest> getTravelsByUserName(List<RegisterRequest> users){

        for (RegisterRequest user : users) {
            List<TravelRequest> potentialTravels = getTravelsByCountry(user.userCountry());

            // Filtre les destinations en fonction des préférences météorologiques de l'utilisateur
            List<TravelRequest> filteredDestinations = potentialTravels.stream()
                .filter(travels -> {
                    double temperature = travels.temperature();
                    return temperature >= - user.minimumTemperatureDistance();
                })
                .collect(Collectors.toList());
            travels.addAll(filteredDestinations);
        }
        return travels;
    }

    List<TravelRequest> getTravelsByCountry(String country) {
        return travels.stream()
            .filter(destination -> destination.country().equals(country))
            .collect(Collectors.toList());
    }
}
