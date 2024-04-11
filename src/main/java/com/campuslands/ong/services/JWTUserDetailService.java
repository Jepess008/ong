package com.campuslands.ong.services;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campuslands.ong.repositories.RepositoryUser;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class JWTUserDetailService implements UserDetailsService {

    private final RepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repositoryUser.findByEmail(username)
                .map(usuario -> {
                    final var authorities = usuario.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .toList();
                    return new User(usuario.getEmail(), usuario.getPassword(), authorities);
                }).orElseThrow(() -> new UsernameNotFoundException("User not exist"));                
        }

    
}
