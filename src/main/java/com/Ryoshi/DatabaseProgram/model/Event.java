package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;
    private int event_day;
    private int event_month;
    private int event_year;
    private String title;
    private String content;
    private String starting;
    private String ending;
    @ManyToOne
    private Dogs dog;

    public Event(long id, int event_day, int event_month, int event_year, String title, String content, String starting, String ending, Dogs dog) {
        this.id = id;
        this.event_day = event_day;
        this.event_month = event_month;
        this.event_year = event_year;
        this.title = title;
        this.content = content;
        this.starting = starting;
        this.ending = ending;
        this.dog = dog;
    }

    public Event() {
    }
    public int getEvent_month() {
        return event_month;
    }

    public void setEvent_month(int month) {
        this.event_month = month;
    }

    public int getEvent_year() {
        return event_year;
    }

    public void setEvent_year(int year) {
        this.event_year = year;
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

    public int getEvent_day() {
        return event_day;
    }

    public void setEvent_day(int date) {
        this.event_day = date;
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
