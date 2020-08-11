package myproject.model.service;

import myproject.model.entity.UserEntity;
import myproject.service.UserServiceModel;

import java.util.List;

public interface UserService {



    UserServiceModel findByUsername(String email);


    List<String> findAllUsernames();


    void addRoleToUser(String username, String role);

    UserServiceModel findById(String id);

    //New impl

    boolean existsUser(String email);

    UserEntity getOrCreateUser(String email);

    void createAndLoginUser(String email, String password);

    UserEntity createUser(String email, String password);

    UserEntity createUser(String email);

    //UserServiceModel registerUser(UserServiceModel userServiceModel);
}
