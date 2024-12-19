package com.example.AcademiFlow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    private Date evaluationDate;

    private int score;

    private Double logic;

    private Double grasping;

    private Double communication;

    private Double assignments;

    private Double provocativeness;

    private Double problemSolving;

    private String comments;

    private Double averageScore;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users student;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Users trainer;

    private boolean isDeleted;
}
