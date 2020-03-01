package com.airline.service;

import com.airline.entity.Passengers;
import com.airline.entity.Role;
import com.airline.repository.PassengersDao;
import com.airline.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengersDao passengersDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Passengers passengers = passengersDao.findByLogin(login);
            List<Role> roles = roleDao.getRolesByPassengersId(passengers.getId());
            if (passengers.getId() == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", login));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        passengers.getLogin(),
                        passengers.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getRole())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
