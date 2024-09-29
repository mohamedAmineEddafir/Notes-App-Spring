package com.secure.notes.controller;

import com.secure.notes.models.Notes;
import com.secure.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesAppController {
    @Autowired
    private NotesService notesService;

    @PostMapping
    public Notes createNotes(@RequestBody String content,
                             @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println(username);
        return notesService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Notes> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS "+username);
        return notesService.getAllNotes(username);
    }

    @PutMapping("/{noteId}")
    public Notes updateNotes(@PathVariable Long noteId,
                             @RequestBody String content ,
                             @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return notesService.updateNoteForUser(noteId, content, username);

    }

    @DeleteMapping("/{noteId}")
    public void deleteNotes(@PathVariable Long noteId,
                            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("DELETE NOTE "+noteId);
        notesService.deleteNoteForUser(noteId, username);
    }
}
