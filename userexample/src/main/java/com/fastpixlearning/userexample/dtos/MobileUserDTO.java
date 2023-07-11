package com.fastpixlearning.userexample.dtos;

import lombok.Data;

import java.util.UUID;


@Data
public class MobileUserDTO {
    private UUID mobileId;
    private String mobileName;
    private String mobileMadeIn;
    private String mobileModel;
    private Integer mobileYearOfManufacture;
    private UUID userId;
    private String userName;
    private Integer userAge;
    private String userGender;

    public MobileUserDTO(UUID mobileId,String mobileName,String mobileMadeIn,String mobileModel,Integer mobileYearOfManufacture,UUID userId,String userName,Integer userAge,String userGender){
        this.mobileId=mobileId;
        this.mobileName=mobileName;
        this.mobileMadeIn=mobileMadeIn;
        this.mobileModel=mobileModel;
        this.mobileYearOfManufacture=mobileYearOfManufacture;
        this.userId=userId;
        this.userName=userName;
        this.userAge=userAge;
        this.userGender=userGender;
    }

}
