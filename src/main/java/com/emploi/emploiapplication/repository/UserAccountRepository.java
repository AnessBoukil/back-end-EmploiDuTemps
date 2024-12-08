package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findByUsername(String username);
}
