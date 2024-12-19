package com.example.AcademiFlow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String setPasswordKey;

    private String username;
    private String password;
    private String profilePictureUrl;

    @ManyToMany
    @JoinTable(name = "user_technology",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technology;

    @Enumerated(EnumType.STRING)
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
//
    @OneToMany(mappedBy = "trainer")
    @JsonBackReference
    private Set<Course> trainingCourses;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> ongoingCourses;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> attendedCourses;

    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private Set<Evaluation> evaluations;

    private boolean isDeleted;

}
