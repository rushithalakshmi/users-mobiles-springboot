package com.fastpixlearning.userexample.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private Integer age;
    private String gender;



    public UserDTO(){}



    public UserDTO(UUID id,String name,Integer age,String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender= gender;
    }
}
