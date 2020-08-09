package myproject.model.service.impl;

import myproject.model.entity.UserEntity;
import myproject.model.service.RoleService;
import myproject.model.service.UserService;
import myproject.repository.UserRepository;
import myproject.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }



    @Override
    public UserServiceModel findByUsername(String email) {
        return this.userRepository.findOneByEmail(email)
                .map(user->this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }


    @Override
    public List<String> findAllUsernames() {

        return this.userRepository.findAll()
                .stream().map(UserEntity::getEmail)
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String role) {

    }

    @Override
    public UserServiceModel findById(String id) {

        return this.userRepository.findById(id)
                .map(user->this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }
}
