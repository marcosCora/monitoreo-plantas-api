package com.api.techforb.security;

import com.api.techforb.entity.Role;
import com.api.techforb.entity.User;
import com.api.techforb.repository.IRepositoryUser;
import jakarta.persistence.AssociationOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    private IRepositoryUser userRepository;

    @Autowired
    public CustomUsersDetailsService(IRepositoryUser userRepo){
        this.userRepository = userRepo;
    }

    public Collection<? extends GrantedAuthority> mapToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTypeRole())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String emailUser) throws UsernameNotFoundException {
        User userRepo = userRepository.findByEmail(emailUser).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(userRepo.getEmail(), userRepo.getPassword(), mapToAuthorities(userRepo.getRoles()));
    }
}
