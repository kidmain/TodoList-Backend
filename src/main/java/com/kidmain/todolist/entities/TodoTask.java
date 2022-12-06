package com.kidmain.todolist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String task;

    @Column
    private Boolean isDone;

    public TodoTask() {
    }

    public TodoTask(Long id, String task, Boolean isDone) {
        this.id = id;
        this.task = task;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "TodoTask{" + "id=" + id +
                ", task='" + task + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
