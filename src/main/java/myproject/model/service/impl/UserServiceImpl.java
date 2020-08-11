package myproject.model.service.impl;

import myproject.model.entity.Role;
import myproject.model.entity.UserEntity;
import myproject.model.service.RoleService;
import myproject.model.service.UserService;
import myproject.repository.UserRepository;
import myproject.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);



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

    @Override
    public boolean existsUser(String email) {
        Objects.requireNonNull(email);

        return userRepository.findOneByEmail(email).isPresent();
    }

    @Override
    public UserEntity getOrCreateUser(String email) {
        Objects.requireNonNull(email);

        Optional<UserEntity> userEntityOpt =
                userRepository.findOneByEmail(email);

        return userEntityOpt.
                orElseGet(() -> createUser(email));
    }

    @Override
    public void createAndLoginUser(String email, String password) {

        UserEntity newUser = createUser(email, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public UserEntity createUser(String email, String password) {
        LOGGER.info("Creating a new user with email [GDPR].");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        if (password != null) {
            userEntity.setPassword(passwordEncoder.encode(password));
        }

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");

        userEntity.setRole(Set.of(userRole));

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity createUser(String email) {
        return  createUser(email, null);
    }

//    @Override
//    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
//        userServiceModel.setRole(this.roleService.findOneByRole("ROLE_USER"));
//
//        UserEntity user=this.modelMapper.map(userServiceModel,UserEntity.class);
//
//        this.userRepository.saveAndFlush(user);
//
//
//
//        return this.modelMapper
//                .map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
//    }
}
