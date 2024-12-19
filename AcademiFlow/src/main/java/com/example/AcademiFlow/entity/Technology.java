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
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technologyId;

    private String technologyName;

    @ManyToMany
    @JoinTable(name = "technology_users",
    joinColumns = @JoinColumn(name = "technology_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Users> users;
}

