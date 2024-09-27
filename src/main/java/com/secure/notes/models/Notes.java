package com.secure.notes.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Notes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) // @GeneratedValue to indicate that the ID should be generated automatically.
    private Long id;
    @Lob
    private String content;
    private String userName;

    public Notes() {}
    public Notes(Long id, String userName, String content) {
        this.id = id;
        this.userName = userName;
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(id, notes.id) && Objects.equals(content, notes.content) && Objects.equals(userName, notes.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userName);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
