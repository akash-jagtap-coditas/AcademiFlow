package com.example.AcademiFlow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stakeholder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isDeleted;

    @ManyToMany
    @JoinTable(name = "stakeholders_courses",
    joinColumns = @JoinColumn(name = "stakeholder_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}
