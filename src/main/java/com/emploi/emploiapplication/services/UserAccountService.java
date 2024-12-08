package com.emploi.emploiapplication.services;

import java.util.Map;

public interface UserAccountService {
    Map<String, String> login(Map<String, String> requestMap);
}
