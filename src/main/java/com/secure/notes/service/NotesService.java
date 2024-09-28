package com.secure.notes.service;

import com.secure.notes.models.Notes;

import java.util.List;

public interface NotesService {
    Notes createNoteForUser(String username, String content);
    Notes updateNoteForUser(Long noteId, String username, String content);
    void deleteNoteForUser(Long noteId, String username);
    List<Notes> getAllNotes(String username);
}
