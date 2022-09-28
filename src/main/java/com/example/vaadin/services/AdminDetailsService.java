package com.example.vaadin.services;

import com.example.vaadin.models.Admin;
import com.example.vaadin.repositories.AdminRepository;
import com.example.vaadin.security.AdminDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    private static final Logger log = LoggerFactory.getLogger(AdminDetails.class);


    @Autowired
    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Start find admin with login = {}", username);
        Optional<Admin> admin = adminRepository.findByLogin(username);
        if (admin.isEmpty()) {
            log.debug("Error find admin with login = "+username);
            throw new UsernameNotFoundException("Admin with login " + username + " not found.");
        }
        log.debug("Successfully find admin with login = {}", username);
        return new AdminDetails(admin.get());
    }


}
