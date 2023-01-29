package com.blog;

import com.blog.config.BlogConstants;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class BlogApplicationApisApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplicationApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("ram"));
        try {
            Role role = new Role();
            role.setId(BlogConstants.ADMIN_USER);
            role.setName("ADMIN_USER");

            Role role1 = new Role();
            role1.setId(BlogConstants.NORMAL_USER);
            role1.setName("NORMAL_USER");

            List<Role> roles = List.of(role, role1);

            List<Role> result = roleRepo.saveAll(roles);

            result.forEach(r -> System.out.println(r.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
