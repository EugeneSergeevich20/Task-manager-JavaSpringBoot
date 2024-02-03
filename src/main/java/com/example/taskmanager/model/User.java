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
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long ID;

    @Column(name = "name")
    public String name;

    @Column(name = "surname")
    public String surname;

    @Column(name = "patronymic")
    public String patronymic;

    @Column(name = "email")
    public String email;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "reg_date")
    public LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role role;

    @ManyToMany(mappedBy = "user")
    public List<Project> project;


}
