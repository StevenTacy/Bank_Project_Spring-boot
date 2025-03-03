package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    
    public User findByUsername (String userAccount) {
    	return userRepository.findByUsername(userAccount);
    }
  
    public void updateUser(String email, User updatedUser) {
        Optional<User> existingUserOpt = Optional.of(userRepository.findByUsername(email));
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setDepositAccount(updatedUser.getDepositAccount());
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }
    
    public void deleteUser(String email) {
        Optional<User> existingUserOpt = Optional.of(userRepository.findByUsername(email));
        existingUserOpt.ifPresent(userRepository::delete);
    }
}
