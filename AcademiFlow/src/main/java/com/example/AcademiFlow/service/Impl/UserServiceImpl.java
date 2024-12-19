package com.example.AcademiFlow.service.Impl;

import com.example.AcademiFlow.dto.UserDto;
import com.example.AcademiFlow.entity.*;
import com.example.AcademiFlow.exception.EmailAlreadyExistsException;
import com.example.AcademiFlow.exception.UserNotFoundException;
import com.example.AcademiFlow.mapper.UserMapper;
import com.example.AcademiFlow.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private EmailService emailService;
    private final UserPaginationRepository userPaginationRepository;
    private final TemplateEngine templateEngine;
    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, ObjectMapper objectMapper, EmailService emailService, UserPaginationRepository userPaginationRepository, TemplateEngine templateEngine) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.userPaginationRepository = userPaginationRepository;
        this.templateEngine = templateEngine;
    }

    public void sendPasswordSetupEmail(Users user) {
        String passwordSetupUrl = String.format("http://localhost:9292/academiflow/users/setpassword?key=".concat(user.getSetPasswordKey()));
        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("passwordSetupUrl", passwordSetupUrl);
        String htmlContent = templateEngine.process("welcome-email", context);
        emailService.sendEmail(user.getEmail(), "Set Your Password", htmlContent);
    }

    public void createSuperAdminIfNotExist() {
        Users existingSuperAdmin = usersRepository.findUserByUsername("superadmin@gmail.com");

        if (existingSuperAdmin == null) {
            var superAdminRole = roleRepository.findByRoleName("SUPER_ADMIN");
            if (superAdminRole == null) {
                throw new RuntimeException("SUPER_ADMIN role not found!");
            }
            Users superAdmin = new Users();
            superAdmin.setEmail("superadmin@gmail.com");
            superAdmin.setName("Super Admin");
            superAdmin.setUsername("superadmin@gmail.com");
            superAdmin.setPassword(passwordEncoder.encode("123456789"));
            superAdmin.setDeleted(false);
            superAdmin.setRoles(Set.of(superAdminRole));
            usersRepository.save(superAdmin);
        }
    }


    public UserDto createUser(UserDto userDto) {
         Users user = userMapper.mapToUser(userDto);
         Users savedUser = null;
         try{
              savedUser = usersRepository.save(user);
         }catch (DataIntegrityViolationException e){
             throw new EmailAlreadyExistsException(user.getEmail());
         }
         UserDto resultDto = userMapper.mapToUserDto(savedUser);
         sendPasswordSetupEmail(savedUser);
         return resultDto; }


    public void setPassword(String key, String newPassword) {
        Users user = usersRepository.findUserByUUID(key);
        if (user == null) {
            throw new UserNotFoundException();
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encryptedPassword);

        usersRepository.save(user);
    }


    public void deleteUser(String username){
        Users User = usersRepository.findUserByUsername(username);
        User.setDeleted(true);
        usersRepository.save(User);
    }

    public List<String> getRoles(String username) {
        Users user = usersRepository.findUserByUsername(username);
        Set<Role> roles = user.getRoles();
        return roles.stream() .map(Role::getRoleName).collect(Collectors.toList());
    }

    public void uploadProfilePicture(MultipartFile file,Long userId) throws IOException {
        Optional<Users> existinguser = usersRepository.findById(userId);
        if(existinguser.isEmpty()){
            throw new UserNotFoundException();
        }
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("src/main/resources/static/images/"+fileName);
        Files.copy(file.getInputStream(),path);
        existinguser.get().setProfilePictureUrl("/images/"+fileName);
        usersRepository.save(existinguser.get());
    }

    public List<UserDto> getAlUsers(Long pageNo, String sort, String filter){
        PageRequest pageRequest = PageRequest.of(Math.toIntExact(pageNo),3);
        Page<Users> usersPage = null;
         if(sort==null && filter==null){
             usersPage = userPaginationRepository.findAll(pageRequest);
         }
        else if(sort.equalsIgnoreCase("name") && filter==null){
            usersPage = userPaginationRepository.findAllByName(pageRequest);
        } else if (sort.equalsIgnoreCase("email") && filter==null) {
            usersPage = userPaginationRepository.findAllByEmail(pageRequest);
        }
        List<UserDto> userDtoList = usersPage.stream()
                .map(users -> userMapper.mapToUserDto(users))
                .collect(Collectors.toList());
        return userDtoList;
    }

}
