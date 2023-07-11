package com.fastpixlearning.userexample.controllers;


import com.fastpixlearning.userexample.dtos.UserDTO;
import com.fastpixlearning.userexample.entities.User;
import com.fastpixlearning.userexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> findByKeyword(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "page") Integer page,
                                    @RequestParam(value = "size") Integer size){
        return  userService.getUsersByName(name,page,size);
    }

    /*@GetMapping
    public ArrayList<UserDTO> getUsers(){
        return userService.getUsers();
    }*/

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable ("userId") UUID id){
        return userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    public UserDTO updateSong(@PathVariable ("userId") UUID id,@RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ("userId") UUID id){
        userService.deleteUser(id);
    }


}
