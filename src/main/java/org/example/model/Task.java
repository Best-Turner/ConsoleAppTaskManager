package org.example.model;

import java.time.LocalDateTime;

public class Task {
    private long id;
    private String title;
    private String description;

    private Priority priority;
    private LocalDateTime date;

    public Task() {
    }

    public Task(long id, String title, String description, Priority priority, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static enum Priority{
        LOW, MIDDLE, HARD
    }

    @Override
    public String toString() {
        return "Task{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", priority=" + priority +
               ", date=" + date +
               '}';
    }
}
