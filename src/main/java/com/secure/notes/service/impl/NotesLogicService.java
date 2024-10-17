package com.secure.notes.service.impl;

import com.secure.notes.models.Notes;
import com.secure.notes.repository.NotesRepository;
import com.secure.notes.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesLogicService implements NotesService {

    //Call NotesRepository make the as record
    private final NotesRepository notesRepository;

    public NotesLogicService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public Notes createNoteForUser(String username, String content) {
        Notes notes = new Notes();
        notes.setContent(content);
        notes.setUsername(username);
        return notesRepository.save(notes);
    }

    @Override
    public Notes updateNoteForUser(Long noteId, String username, String content) {
        Notes notes = notesRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        notes.setContent(content);
        return notesRepository.save(notes);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        notesRepository.deleteById(noteId);
    }

    // Show All Notes For Each User's by here name's
    @Override
    public List<Notes> getAllNotes(String username) {
        return notesRepository.findByUsername(username);
    }
}
