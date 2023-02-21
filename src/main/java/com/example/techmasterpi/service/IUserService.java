package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.User;

import java.util.List;

public interface IUserService {

    User addUser (User user);
    List<User> retrieveAllUsers();

    User retrieveUserById(Integer userid);

    void deleteUser(Integer userid);

   /* User updateUser(User user);*/
}
