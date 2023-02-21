package com.example.techmasterpi.Controller;

import com.example.techmasterpi.domain.User;
import com.example.techmasterpi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/retrieve-all-users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> listUsers = userService.retrieveAllUsers();
        return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/retrieve-user/{user-id}")
    public ResponseEntity<User> getUserById(@PathVariable("user-id") Integer userid) {
        User u = userService.retrieveUserById(userid);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    @Transactional
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User was successfully added");
    }

    @DeleteMapping("/remove-user/{user-id}")
    @Transactional
    public ResponseEntity<String> removeEtudiant(@PathVariable("user-id") Integer userid) {
        userService.deleteUser(userid);
        return new ResponseEntity<String>("User with '"+userid+"' has been sucessfully deleted",HttpStatus.OK);
    }
}
