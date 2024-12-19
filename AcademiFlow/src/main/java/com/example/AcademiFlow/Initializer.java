package com.example.AcademiFlow;

import com.example.AcademiFlow.service.Impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    private final UserServiceImpl userServiceImpl;

    public Initializer(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception {
        userServiceImpl.createSuperAdminIfNotExist();
    }
}
