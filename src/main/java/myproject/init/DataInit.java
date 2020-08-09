package myproject.init;

import myproject.model.entity.*;

import myproject.repository.CategoryRepository;
import myproject.repository.ProductRepository;
import myproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class DataInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInit(UserRepository userRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        if (announcementRepository.count() == 0) {
//            AnnouncementEntity announcementEntity = new AnnouncementEntity();
//            announcementEntity.setTitle("Hello, Softuni!");
//            announcementEntity.setDescription("Welcome to the Srping advacned course!");
//            announcementEntity.setCreatedOn(Instant.now());
//            announcementEntity.setUpdatedOn(Instant.now());
//            announcementRepository.save(announcementEntity);
//        }

        if (this.categoryRepository.count()==0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("MUSIC %s",categoryName.name())));
                    });
        }

        if (userRepository.count() == 0) {
            // admin
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("svetlio"));

            Role adminAdminRole = new Role();
            adminAdminRole.setRole("ROLE_ADMIN");

            Role adminUserRole = new Role();
            adminUserRole.setRole("ROLE_USER");

            admin.setRole(Set.of(adminAdminRole, adminUserRole));

            userRepository.save(admin);


            UserEntity user = new UserEntity();
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user"));

            Role userUserRole = new Role();
            userUserRole.setRole("ROLE_USER");

            user.setRole(Set.of(userUserRole));

            userRepository.save(user);

        }



        if (productRepository.count()==0){
            Product pro1=new Product();
            pro1.setName("violin");

            pro1.setCategory(this.categoryRepository
                    .findByCategoryName(CategoryName.ACCESSORIES).orElse(null));
            pro1.setStatus("New");
            pro1.setManufacturer("SBN1");
            pro1.setDescription("The latest version of 6 stages guitar");
            pro1.setPrice(BigDecimal.valueOf(100));
//          pro1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg/560px-2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg");
            productRepository.saveAndFlush(pro1);
        }
    }
}
