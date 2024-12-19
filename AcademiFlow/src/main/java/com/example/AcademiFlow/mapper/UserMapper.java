package com.example.AcademiFlow.mapper;

import com.example.AcademiFlow.dto.UserDto;
import com.example.AcademiFlow.entity.Location;
import com.example.AcademiFlow.entity.Role;
import com.example.AcademiFlow.entity.Technology;
import com.example.AcademiFlow.entity.Users;
import com.example.AcademiFlow.repository.RoleRepository;
import com.example.AcademiFlow.repository.TechnoogyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TechnoogyRepository technoogyRepository;

    public Users mapToUser(UserDto userDto){
        Users user = new Users();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getEmail());
        Set<Technology> technology = technoogyRepository.findByTechnologyName(userDto.getTechnology());
        user.setTechnology(technology);
        user.setLocation(Location.valueOf(userDto.getLocation().toUpperCase()));
        user.setDeleted(false);
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        if(userDto.isAdmin()){
            role = roleRepository.findByRoleName("ADMIN");
            roles.add(role);
            role = roleRepository.findByRoleName("STUDENT");
            roles.add(role);
        }else {
            role = roleRepository.findByRoleName("STUDENT");
           roles = Set.of(role);
        }
        user.setRoles(roles);
        String signUpUid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        user.setSetPasswordKey(signUpUid);
        return user;
    }

    public UserDto mapToUserDto(Users user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        user.setTechnology(user.getTechnology());
        userDto.setLocation(String.valueOf(user.getLocation()));
        List<String> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        userDto.setRoles(roles);
        return userDto;
    }
}
