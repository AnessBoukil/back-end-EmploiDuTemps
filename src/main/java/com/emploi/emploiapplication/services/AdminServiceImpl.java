package com.emploi.emploiapplication.services;


import com.emploi.emploiapplication.entities.Admin;
import com.emploi.emploiapplication.entities.UserAccount;
import com.emploi.emploiapplication.repository.AdminRepository;
import com.emploi.emploiapplication.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private UserAccountRepository userAccountRepository;
    @Override
    public Admin getAdmin(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount==null) throw new RuntimeException("Username not found");
        return adminRepository.getReferenceById(userAccount.getUser().getIdUser());
    }
}
