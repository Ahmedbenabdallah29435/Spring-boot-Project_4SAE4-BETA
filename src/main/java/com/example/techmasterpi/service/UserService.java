package com.example.techmasterpi.service;

import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User retrieveUserById(Integer userid) {
        return userRepository.findById(userid).orElse(null);
    }

    @Override
    public void deleteUser(Integer userid) {
        User u= retrieveUserById(userid);
        userRepository.delete(u);
    }

   /* @Override
    public User updateUser(User user) {
        return null;
    }*/
}
