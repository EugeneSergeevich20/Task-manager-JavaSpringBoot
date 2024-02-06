package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long ID;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public StatusTask status;

    @Column(name = "deadline")
    public LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    public PriorityTask priority;

    @Column(name = "create_date")
    public LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    public Project project;

}
