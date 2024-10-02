package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_seq") // using GenerationType.SEQUENCE might be more consistent across various database implementations
    @SequenceGenerator(name = "notes_seq", sequenceName = "notes_seq", allocationSize = 1)
    private Long id;

    /*
        The @Lob annotation is used to store large objects,
        either BLOB (binary large object) or CLOB (character large object).
        Since your content field is a string, it’s mapped to a CLOB.
        However, depending on the database you’re using,
        @Lob may require a columnDefinition to specify how the text should be stored
    */

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String username;

    public Notes() {}
    public Notes(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(id, notes.id) && Objects.equals(content, notes.content) && Objects.equals(username, notes.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, username);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
