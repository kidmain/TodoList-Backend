package com.kidmain.todolist.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todolist_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task")
    @NotNull(message = "Action must not be null")
    @NotBlank(message = "Action must not be blank")
    @NotEmpty(message = "Action must not be empty")
    @Size(min = 3, message = "Action must have more than 3 characters")
    private String action;

    @Column(name = "is_done")
    private boolean isDone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TodoUser user;
}
