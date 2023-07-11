package com.fastpixlearning.userexample.dtos;


import lombok.Data;

import java.util.UUID;

@Data
public class MobileDTO {
    private UUID id;
    private String name;
    private String madeIn;
    private String model;
    private Integer yearOfManufacture;
    private UUID userId;
    public MobileDTO(){}

    public MobileDTO(UUID id,String name,String madeIn,String model,Integer yearOfManufacture,UUID userId){
        this.id=id;
        this.name=name;
        this.madeIn=madeIn;
        this.model=model;
        this.yearOfManufacture=yearOfManufacture;
        this.userId=userId;
    }

}