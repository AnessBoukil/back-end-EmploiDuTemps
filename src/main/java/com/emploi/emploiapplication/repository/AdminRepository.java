package com.emploi.emploiapplication.repository;

import com.emploi.emploiapplication.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("select e from Admin e where e.userAccount.username = ?1")
    Admin findByUsername(String username);
}
