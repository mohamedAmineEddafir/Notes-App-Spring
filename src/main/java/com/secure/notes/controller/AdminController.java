package com.secure.notes.controller;

import com.secure.notes.models.User;
import com.secure.notes.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = usersService.getUsers();
        return ResponseEntity.ok(users);
    }




}
