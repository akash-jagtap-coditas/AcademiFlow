package com.example.AcademiFlow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String username;

    @JsonIgnore
    private boolean admin;
    private Set<String> technology;
    private String profilePicture;
    private String location;
    private List<String> roles;

    @JsonIgnore
    private boolean isDeleted;

}
