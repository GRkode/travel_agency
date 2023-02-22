package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private final List<RegisterRequest> registerList = new ArrayList<>();

    public List<RegisterRequest> save(RegisterRequest request) {
        registerList.add(request);
        return registerList;
    }

    public List<RegisterRequest> getRegisters() {
        return registerList;
    }

    public List<RegisterRequest> findByUserName(String userName){
        return registerList.stream()
            .filter(user -> user.userName().equals(userName))
            .collect(Collectors.toList());
    }
}
