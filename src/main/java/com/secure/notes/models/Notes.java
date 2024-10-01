package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Data
public class Notes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) // @GeneratedValue to indicate that the ID should be generated automatically.
    private Long id;
    @Lob
    private String content;
    private String username;

    public Notes() {}
    public Notes(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
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
