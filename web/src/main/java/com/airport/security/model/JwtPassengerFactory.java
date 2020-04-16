package com.airport.security.model;

import com.airport.entity.Passenger;
import com.airport.entity.Role;
import com.airport.entity.Status;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtPassengerFactory {

    public static JwtPassenger create(Passenger passenger) {
        return new JwtPassenger(
                passenger.getId(),
                passenger.getLogin (),
                passenger.getName (),
                passenger.getSecondName (),
                passenger.getPassword(),
                passenger.getStatus().equals(Status.ACTIVE),
                mapToGrantedAuthorities(new HashSet<> (passenger.getRole ()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority (role.getRole ().name ())
                ).collect(Collectors.toList());
    }
}
