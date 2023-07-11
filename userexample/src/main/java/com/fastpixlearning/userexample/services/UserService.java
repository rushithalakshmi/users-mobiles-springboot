package com.fastpixlearning.userexample.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import com.fastpixlearning.userexample.entities.User;
import com.fastpixlearning.userexample.entities.UserRowMapper;
import com.fastpixlearning.userexample.dtos.UserDTO;
import com.fastpixlearning.userexample.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate db;

    @Autowired
    private UserRepository userRepository;





    public List<User> getUsersByName(String name,int page, int size) {
        int offset=0;
        if(page>0){
            offset = (page - 1) * size;
        }
        return userRepository.findAllByNameContaining(name,size,offset);


    }

    public ArrayList<UserDTO> getUsers(){
        List<User> userList=db.query("select * from users",new UserRowMapper() );
        ArrayList<User> users =new ArrayList<>(userList);
        ArrayList<UserDTO> userDTOs =new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getAge(),user.getGender());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public UserDTO addUser(UserDTO userDTO){
        UUID uuid = UUID.randomUUID();
        db.update("insert into users(id,name,age,gender) values(?,?,?,?)",uuid,userDTO.getName(),userDTO.getAge(),userDTO.getGender());
        User savedUser=db.queryForObject("select * from users where name=? and age=? and gender=?",new UserRowMapper(),userDTO.getName(),userDTO.getAge(),userDTO.getGender());
        UserDTO savedUserDTO=new UserDTO(savedUser.getId(),savedUser.getName(),savedUser.getAge(),savedUser.getGender());
        return savedUserDTO;
    }

    public UserDTO getUserById(UUID id){
        try{
            User user=db.queryForObject("select * from users where id=?",new UserRowMapper(),id);
            UserDTO userDTO=new UserDTO(user.getId(),user.getName(),user.getAge(),user.getGender());
            return userDTO;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


    public UserDTO updateUser(UUID id,UserDTO userDTO){
        if(userDTO.getName()!=null){
            db.update("update users set name=? where id=? ",userDTO.getName(),id);
        }
        if(userDTO.getAge()!=null){
            db.update("update users set age=? where id=? ",userDTO.getAge(),id);
        }
        if(userDTO.getGender()!=null){
            db.update("update users set gender=? where id=? ",userDTO.getGender(),id);
        }
        return getUserById(id);
    }

    public void deleteUser(UUID id){
        db.update("delete from users where id=? ",id);
    }



}
