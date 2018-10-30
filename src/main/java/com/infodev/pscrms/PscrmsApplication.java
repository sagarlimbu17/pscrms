package com.infodev.pscrms;

/*import com.infodev.pscrms.repository.RoleRepository;
import com.infodev.pscrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PscrmsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PscrmsApplication.class, args);
    }

    /*@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;*/


    @Override
    public void run(String... args) throws Exception {
       /* Role r1 = new Role();
        r1.setName("USER");

        Role r2 = new Role();
        r2.setName("ADMIN");


        roleRepository.save(r1);
        roleRepository.save(r2);

        UserRole userRole = new UserRole();
        userRole.setRole(r1);


        User admin  = new User();
        admin.setUsername("admin");
        admin.setPassword(SecurityUtils.passwordEncoder().encode("admin"));

        UserRole adminRole = new UserRole();
        adminRole.setRole(r2);






        User user2  = new User();
        user2.setUsername("user");
        user2.setPassword(SecurityUtils.passwordEncoder().encode("user"));


        adminRole.setUser(admin);
        userRole.setUser(user2);

        admin.setUserRoles(Arrays.asList(adminRole));
        user2.setUserRoles(Arrays.asList(userRole));



        userRepository.save(user2);
        userRepository.save(admin);
*/

    }
}
