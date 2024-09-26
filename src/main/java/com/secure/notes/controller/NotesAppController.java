package com.secure.notes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesAppController {

    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
