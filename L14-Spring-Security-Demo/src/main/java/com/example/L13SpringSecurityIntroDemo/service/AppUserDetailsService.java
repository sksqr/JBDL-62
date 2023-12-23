package com.example.L13SpringSecurityIntroDemo.service;

import com.example.L13SpringSecurityIntroDemo.IAppUserRepo;
import com.example.L13SpringSecurityIntroDemo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private IAppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = appUserRepo.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }

    public Long createUser(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepo.save(appUser);
        return appUser.getId();
    }

    public void changePassword(UserDetails userDetails, String newPassword){
        AppUser appUser = appUserRepo.findByUsername(userDetails.getUsername());
        appUser.setPassword(passwordEncoder.encode(newPassword));
        appUserRepo.save(appUser);
    }
}
