package com.example.AcademiFlow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Users trainer;

    private String description;

    private String duration;

    private boolean isDeleted;

    @ManyToMany(mappedBy = "ongoingCourses")
    private Set<Users> ongoingStudents;

    private boolean isStarted;

    private boolean isCompleted;

    private String courseLocation;

    @OneToMany(mappedBy = "course")
    private Set<Evaluation> evaluations;

    @ManyToMany(mappedBy = "attendedCourses")
    private Set<Users> students;
}
