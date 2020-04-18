package com.airport.security.model;

import com.airport.entity.Passenger;
import com.airport.entity.Role;
import com.airport.entity.RoleName;
import com.airport.entity.Status;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtPassengerFactory {

    public static JwtPassenger create (Passenger passenger) {
        return new JwtPassenger (
                passenger.getPassword (),
                passenger.getLogin (),
                mapToGrantedAuthorities (new HashSet<> (passenger.getRole ())),
                passenger.getId (),
                passenger.getName (),
                passenger.getSecondName (),
                passenger.getStatus ().equals (Status.ACTIVE));

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities (Set<Role> rolesPassenger) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList (rolesPassenger.stream ()
                                                                                 .map (Role::getRoleName)
                                                                                 .map (RoleName::toString)
                                                                                 .map (s -> s.replace (s, "ROLE_".concat (s)))
                                                                                 .collect (Collectors.joining (",")));

    }
}
