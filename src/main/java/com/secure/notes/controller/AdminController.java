package com.secure.notes.controller;

import com.secure.notes.dtos.UserDTO;
import com.secure.notes.models.User;
import com.secure.notes.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId,
                                                 @RequestParam String roleName) {
        usersService.updateUserRole(userId, roleName);
        return ResponseEntity.ok("Successfully updated");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

}
