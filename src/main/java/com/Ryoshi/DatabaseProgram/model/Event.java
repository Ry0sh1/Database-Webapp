package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String date;
    private String title;
    private String content;
    private String starting;
    private String ending;
    @ManyToOne
    private Dogs dog;

    public Event(long id, String date, String title, String content, String starting, String ending, Dogs dog) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.starting = starting;
        this.ending = ending;
        this.dog = dog;
    }

    public Event() {
    }

    public Dogs getDog() {
        return dog;
    }

    public void setDog(Dogs dog) {
        this.dog = dog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
