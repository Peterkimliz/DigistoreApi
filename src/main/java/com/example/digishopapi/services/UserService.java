package com.example.digishopapi.services;

import com.example.digishopapi.dtos.LoginResponse;
import com.example.digishopapi.security.JwtUtils;
import com.example.digishopapi.dtos.LoginDto;
import com.example.digishopapi.dtos.UserDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.User;
import com.example.digishopapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    public User createUser(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());

        if (user.isPresent()) {
            throw new FoundException("user with the provided email already exists");
        } else {
            User newUser = new User();
            newUser.setUsername(userDto.getUsername());
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newUser.setUserType(userDto.getUserType());
            newUser.setCreatedAt(new Date(System.currentTimeMillis()));
            newUser.setUpdatedAt(new Date(System.currentTimeMillis()));
            newUser.setPhone(userDto.getPhone());
            return userRepository.save(newUser);
        }


    }

    public LoginResponse loginUser(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

        } catch (Exception e) {
            throw new NotFoundException("invalid email or password");
        }
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        String token = jwtUtils.generateToken(loginDto.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user.get());
        loginResponse.setToken(token);

        return loginResponse;
    }


    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("user with the provided id doesn't exist");
        } else {
            return user.get();
        }

    }

    public User updateUserById(String id, User user) {
        Optional<User> foundUser = userRepository.findById(id);

        if (foundUser.isEmpty()) {
            throw new NotFoundException("user with the provided id doesn't exist");
        } else {
            User newUser = foundUser.get();
            newUser.setUsername(user.getUsername() == null ? newUser.getUsername() : user.getUsername());
            newUser.setPhone(user.getPhone() == null ? newUser.getPhone() : user.getPhone());
            newUser.setProfileImage(user.getProfileImage() == null ? newUser.getProfileImage() : user.getProfileImage());
            newUser.setUpdatedAt(new Date(System.currentTimeMillis()));
            return userRepository.save(newUser);
        }

    }

    public void deleteUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        System.out.println("user is " + user);
        if (user.isEmpty()) {
            throw new NotFoundException("user with the provided id doesn't exist");
        } else {
            userRepository.deleteById(id);
        }
    }


    public List<User> getAllUsers(String pageNumber) {
        if (pageNumber == null) {
            return getUsers();
        } else {
            return getPageableShops(Integer.parseInt(pageNumber));
        }

    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (users.size() == 0) {
            return new ArrayList<>();
        } else {
            return users;
        }
    }


    public List<User> getPageableShops(int page) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, 20).withSort(Sort.by(Sort.Direction.DESC, "createdAt")));
        return users.toList();
    }


    public void updateWishList(String productId, String userId) {
        User user = getUserById(userId);
        List<String> productIds = user.getWishlist();
        boolean exists = productIds.stream().anyMatch(e -> e.equals(productId));
        if (exists) {
            productIds.remove(productId);
        } else {
            productIds.add(productId);
        }
        user.setWishlist(productIds);
        userRepository.save(user);

    }

}
