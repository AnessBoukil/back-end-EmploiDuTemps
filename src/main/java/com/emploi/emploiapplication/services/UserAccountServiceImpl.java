package com.emploi.emploiapplication.services;

import com.emploi.emploiapplication.Security.JwtUtils;
import com.emploi.emploiapplication.entities.UserAccount;
import com.emploi.emploiapplication.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserAccountRepository userAccountRepository;

    @Override
    public Map<String, String> login(Map<String, String> requestMap) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"),
                    requestMap.get("password")));

            UserAccount userAccount = userAccountRepository.findByUsername(requestMap.get("username"));
            Map<String, String> token = new HashMap<>();
            token.put("token", jwtUtils.generateToken(userAccount.getUsername(), userAccount.getRole()));
            return token;
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Les identifications sont erronées", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}
