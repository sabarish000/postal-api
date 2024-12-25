package com.example.search.postal.services;

import com.example.search.postal.models.User;
import com.example.search.postal.repositories.UserRepository;
import com.example.search.postal.security.PasswordUtils;
import org.jboss.logging.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    Logger logger = Logger.getLogger(AuthService.class);
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates the user using the provided username and password.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @throws AuthenticationException if the authentication fails.
     * @return User for successful authentication.
     */
    public User authenticate(String username, String password) {
        logger.info("Authenticating user: " + username);
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new AuthenticationException("Invalid username or password") {};
        }

        User user = optionalUser.get();

        if (!PasswordUtils.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid username or password") {};
        }

        return user;
    }
}
