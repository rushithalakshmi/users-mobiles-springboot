package com.fastpixlearning.userexample.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import lombok.Data;
import java.util.UUID;


@Data
@Table("users")
public class User{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //@Column(name = "name")
    private String name;
    //@Column(name = "age")
    private Integer age;
    //@Column(name = "gender")
    private String gender;

    public User(){}
    public User(UUID id,String name,Integer age,String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender= gender;
    }
}


