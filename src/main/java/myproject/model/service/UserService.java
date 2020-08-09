package myproject.model.service;

import myproject.service.UserServiceModel;

import java.util.List;

public interface UserService {



    UserServiceModel findByUsername(String email);


    List<String> findAllUsernames();


    void addRoleToUser(String username, String role);

    UserServiceModel findById(String id);
}
