package com.blog.services.Impl;

import com.blog.DTO.UserDto;
import com.blog.config.BlogConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.RoleRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // model mapper library used to convert one class into another like user to userdto and vice versa
    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        // encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // roles

        Role role = roleRepo.findById(BlogConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User newUser = userRepo.save(user);

        return modelMapper.map(newUser, UserDto.class);

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user1 = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user1);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user1 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        user1.setAbout(userDto.getAbout());

        User updateUser = userRepo.save(user1);
        return this.userToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> user = userRepo.findAll();

        List<UserDto> userDtos = user.stream().map(user1 -> userToDto(user1)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepo.delete(user);

        System.out.println("User deleted successfully");
    }

    private User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;

    }
}
