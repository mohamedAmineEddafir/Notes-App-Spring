package com.secure.notes.service;

import com.secure.notes.models.Notes;
import com.secure.notes.repository.NotesRepository;
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
        Notes note = new Notes();
        note.setContent(content);
        note.setUserName(username);
        return notesRepository.save(note);
    }

    @Override
    public Notes updateNoteForUser(Long noteId, String username, String content) {
        Notes note = notesRepository.findById(noteId).orElseThrow(()
                ->new RuntimeException("Note not found"));
        note.setContent(content);
        return notesRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId) {
        notesRepository.deleteById(noteId);
    }

    // Show All Notes For Each User's by here name's
    @Override
    public List<Notes> getAllNotes(String username) {
        return notesRepository.findByUserName(username);
    }
}
