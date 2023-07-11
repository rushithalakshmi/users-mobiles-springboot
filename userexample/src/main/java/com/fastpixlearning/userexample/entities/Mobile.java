package com.fastpixlearning.userexample.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import lombok.Data;
import java.util.UUID;

@Data
@Table("mobiles")
public class Mobile {
    @Id
    private UUID id;
    private String name;
    private String madeIn;
    private String model;
    private Integer yearOfManufacture;
    private UUID userId;
    public Mobile(){}

    public Mobile(UUID id,String name,String madeIn,String model,Integer yearOfManufacture,UUID userId){
        this.id=id;
        this.name=name;
        this.madeIn=madeIn;
        this.model=model;
        this.yearOfManufacture=yearOfManufacture;
        this.userId=userId;
    }

}
