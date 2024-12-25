package com.example.search.postal.services;

import com.example.search.postal.models.User;

public interface IAuthService {
    User authenticate(String username, String password);
}
