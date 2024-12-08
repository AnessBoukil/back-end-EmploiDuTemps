package com.emploi.emploiapplication;

import com.emploi.emploiapplication.DTO.EnseignantDTO;
import com.emploi.emploiapplication.entities.Admin;
import com.emploi.emploiapplication.entities.Enseignant;
import com.emploi.emploiapplication.entities.UserAccount;
import com.emploi.emploiapplication.mappers.MapperServiceImpl;
import com.emploi.emploiapplication.repository.AdminRepository;
import com.emploi.emploiapplication.repository.EnseignantRepository;
import com.emploi.emploiapplication.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmploiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmploiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AdminRepository adminRepository, UserAccountRepository userAccountRepository ) {
        return args -> {

            Admin admin = new Admin();
            admin.setNom("admin");
            admin.setPrenom("admin");
            admin.setEmail("admin@usms.ac.ma");
            admin.setNTele("+212600000000");
            Admin admin1 = adminRepository.save(admin);
            UserAccount account1=new UserAccount();
            account1.setUsername("admin");
            account1.setPassword("12345");
            account1.setRole("Admin");
            account1.setUser(admin);
            admin1.setUserAccount(userAccountRepository.save(account1));

        };
    }
}
