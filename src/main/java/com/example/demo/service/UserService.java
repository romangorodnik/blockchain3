package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password) throws NoSuchAlgorithmException {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new RuntimeException("Такой пользователь уже существует");
        }

        String hash = hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setHash(hash);

        userRepository.save(user);
    }


    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
