package com.secure.notes.repository;

import com.secure.notes.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByUserName(String userName);
}
