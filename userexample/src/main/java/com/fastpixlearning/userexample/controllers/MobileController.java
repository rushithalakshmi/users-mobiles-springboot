package com.fastpixlearning.userexample.controllers;


import com.fastpixlearning.userexample.dtos.MobileDTO;
import com.fastpixlearning.userexample.entities.Mobile;
import com.fastpixlearning.userexample.services.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.fastpixlearning.userexample.dtos.MobileUserDTO;


@RestController
public class MobileController {
    @Autowired
    public MobileService mobileService;

    @GetMapping("/mobiles")
    public ArrayList<MobileDTO> getMobiles(){
        return mobileService.getMobiles();
    }

    @PostMapping("/mobiles")
    public MobileDTO addMobile(@RequestBody Mobile mobile){
        return mobileService.addMobile(mobile);
    }

    @PostMapping("/mobiles/table")
    public void creatMobileTable(){
        mobileService.creatMobileTable();
    }

    @GetMapping("/mobiles/{mobileId}")
    public MobileDTO getMobileById(@PathVariable ("mobileId") UUID id){
        return mobileService.getMobileById(id);
    }

    @PutMapping("/mobiles/{mobileId}")
    public MobileDTO updateMobile(@PathVariable ("mobileId") UUID id, @RequestBody Mobile mobile){
        return mobileService.updateMobile(id,mobile);
    }

    @DeleteMapping("/mobiles/{mobileId}")
    public void deleteById(@PathVariable ("mobileId") UUID id){
        mobileService.deleteById(id);
    }

    @DeleteMapping("/mobiles/delete")
    public void deleteAll(){
        mobileService.deleteAll();
    }

    @GetMapping("/users/{userId}/mobiles")
    public List<MobileDTO> getMobilesByUserId(@PathVariable ("userId") UUID userId){
        return mobileService.getMobilesByUserId(userId);
    }

    @GetMapping("/users/{userId}/mobile")
    public List<MobileUserDTO>  getMobileUsersDetails(@PathVariable ("userId") UUID userId){
        return mobileService.getMobileUsersDetails(userId);
    }

}
