package com.example.L16OAuth2demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMyUserRepo extends JpaRepository<MyUser,Long> {
    MyUser findByEmail(String email);
}
