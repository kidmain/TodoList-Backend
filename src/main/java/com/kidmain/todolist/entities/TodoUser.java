package com.kidmain.todolist.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "todolist_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotNull(message = "Action must not be null")
    @NotBlank(message = "Action must not be blank")
    @NotEmpty(message = "Action must not be empty")
    @Size(min = 3, message = "Action must have more than 3 characters")
    private String username;

    @Column(name = "total_score")
    private Integer totalScore;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoTask> tasks;

    @Override
    public String toString() {
        return "TodoUser{" + "id=" + id +
                ", username='" + username + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}
