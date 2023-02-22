package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TravelRepository {
    private final List<TravelRequest> travels = new ArrayList<>();

    void addTravel(TravelRequest travelRequest){
        travels.add(travelRequest);
    }

    List<TravelRequest> getTravelsByUserName(List<RegisterRequest> users){
        simulatePotentialTravels();
        Set<TravelRequest> potentialTravels = new HashSet<>();
        users.stream()
            .map(user -> getTravelsByCountry(user.userCountry()))
            .forEach(potentialTravels::addAll);

        return potentialTravels.stream()
            .filter(destination -> {
                double temperature = destination.temperature();
                for (RegisterRequest user : users) {
                    return temperature >= - user.minimumTemperatureDistance();
                }
                return false;
            })
            .collect(Collectors.toList());
    }

    List<TravelRequest> getTravelsByCountry(String country) {
        return travels.stream()
            .filter(destination -> destination.country().equals(country))
            .collect(Collectors.toList());
    }

    void simulatePotentialTravels() {
        travels.add(new TravelRequest("France", 20.0));
        travels.add(new TravelRequest("Australia", 25.0));
        travels.add(new TravelRequest("Caribbean", 30.0));
    }
}
