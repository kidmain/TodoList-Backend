package com.kidmain.todolist.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
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

    @Column
    private String password;

    @Column(name = "total_score")
    private Integer totalScore;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoTask> tasks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<TodoRole> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "TodoUser{" + "id=" + id +
                ", username='" + username + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}
