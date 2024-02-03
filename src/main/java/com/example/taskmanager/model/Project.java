package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long ID;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "create_date")
    public LocalDateTime createDate;

    @ManyToMany
    @JoinTable(name = "tb_user_project",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> user;

}
