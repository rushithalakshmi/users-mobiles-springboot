package com.fastpixlearning.userexample.services;



import com.fastpixlearning.userexample.dtos.MobileDTO;
import com.fastpixlearning.userexample.entities.Mobile;
import com.fastpixlearning.userexample.entities.MobileRowMapper;
import com.fastpixlearning.userexample.dtos.MobileUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MobileService {

    @Autowired
    private JdbcTemplate db;

    public void creatMobileTable(){
        db.update("CREATE TABLE mobiles (id UUID PRIMARY KEY,name VARCHAR(255),made_in VARCHAR(255),model VARCHAR(255),year INTEGER,user_id UUID )");
    }
    public ArrayList<MobileDTO> getMobiles(){
        List<Mobile> mobileList = db.query("select * from mobiles ", new MobileRowMapper());
        ArrayList<Mobile> mobiles=new ArrayList<>(mobileList);
        ArrayList<MobileDTO> mobileDTOs=new ArrayList<>();
        for(Mobile mobile : mobiles){
            MobileDTO mobileDTO=new MobileDTO(mobile.getId(),mobile.getName(),mobile.getMadeIn(), mobile.getModel(), mobile.getYearOfManufacture(),mobile.getUserId());
            mobileDTOs.add(mobileDTO);
        }
        return mobileDTOs;
    }
    public MobileDTO addMobile(Mobile mobile){
        UUID uuid = UUID.randomUUID();
        db.update("insert into mobiles(id,name,made_in,model,year,user_id) values(?,?,?,?,?,?)",uuid,mobile.getName(),mobile.getMadeIn(),mobile.getModel(),mobile.getYearOfManufacture(),mobile.getUserId());
        Mobile savedMobile=db.queryForObject("select * from mobiles where id=?",new MobileRowMapper(),uuid);
        MobileDTO mobileDTO=new MobileDTO(uuid,mobile.getName(),mobile.getMadeIn(), mobile.getModel(), mobile.getYearOfManufacture(),mobile.getUserId());
        return mobileDTO;
    }

    public MobileDTO getMobileById(UUID id){
        try{
            Mobile mobile=db.queryForObject("select * from mobiles where id=?",new MobileRowMapper(),id);
            MobileDTO mobileDTO=new MobileDTO(mobile.getId(),mobile.getName(),mobile.getMadeIn(), mobile.getModel(), mobile.getYearOfManufacture(),mobile.getUserId());

            return mobileDTO;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public void deleteAll(){
        db.update("delete from mobiles");
    }

    public void deleteById(UUID id){
        db.update("delete from mobiles where id=? ",id);
    }


    public List<MobileDTO> getMobilesByUserId(UUID userId) {
        String sql = "SELECT * FROM mobiles where user_id=?";
        List<Mobile> mobileList=db.query(sql,new MobileRowMapper(),userId );
        ArrayList<Mobile> mobiles=new ArrayList<>(mobileList);
        ArrayList<MobileDTO> mobileDTOs=new ArrayList<>();
        for(Mobile mobile : mobiles){
            MobileDTO mobileDTO=new MobileDTO(mobile.getId(),mobile.getName(),mobile.getMadeIn(), mobile.getModel(), mobile.getYearOfManufacture(),mobile.getUserId());
            mobileDTOs.add(mobileDTO);
        }

        return mobileDTOs;
    }


    public MobileDTO updateMobile(UUID id, Mobile mobile){
        if(mobile.getName()!=null){
            db.update("update mobiles set name=? where id=? ",mobile.getName(),id);
        }
        if(mobile.getMadeIn()!=null){
            db.update("update mobiles  set made_in=? where id=? ",mobile.getMadeIn(),id);
        }
        if(mobile.getModel()!=null){
            db.update("update mobiles  set model=? where id=? ",mobile.getModel(),id);
        }
        if(mobile.getYearOfManufacture()!=null){
            db.update("update mobiles  set year=? where id=? ",mobile.getYearOfManufacture(),id);
        }
        if(mobile.getUserId()!=null){
            db.update("update mobiles  set user_id=? where id=? ",mobile.getUserId(),id);
        }
        return getMobileById(id);
    }

    public List<MobileUserDTO>  getMobileUsersDetails(UUID userId){
        String sql = "SELECT m.id, m.name, m.made_in, m.model, m.year, m.user_id,u.name,u.age,u.gender  FROM mobiles m JOIN users u ON m.user_id = u.id WHERE u.id = ?";
        return db.query(sql,bookRowMapper ,userId);
    }

    private final RowMapper<MobileUserDTO> bookRowMapper = (rs, rowNum) -> {
        UUID mobileId=UUID.fromString(rs.getString("id"));
        String mobileName=rs.getString("name");
        String mobileMadeIn=rs.getString("made_in");
        String mobileModel=rs.getString("model");
        Integer mobileYearOfManufacture=rs.getInt("year");
        UUID userId=UUID.fromString(rs.getString("user_id"));
        String userName=rs.getString("name");
        Integer userAge=rs.getInt("age");
        String userGender=rs.getString("gender");


        return new MobileUserDTO(mobileId,mobileName,mobileMadeIn,mobileModel,mobileYearOfManufacture,userId,userName,userAge,userGender);
    };







}
