package com.example.L13SpringSecurityIntroDemo;

import com.example.L13SpringSecurityIntroDemo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepo extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);
}
