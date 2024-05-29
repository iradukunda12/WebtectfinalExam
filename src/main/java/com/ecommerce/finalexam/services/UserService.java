package com.ecommerce.finalexam.services;

import com.ecommerce.finalexam.dto.UserDto;
import com.ecommerce.finalexam.user.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}