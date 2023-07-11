package com.fastpixlearning.userexample.entities;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fastpixlearning.userexample.services.UserService;

public class MobileRowMapper implements RowMapper<Mobile> {
    @Autowired
    private UserService userService;
    @Override
    public Mobile mapRow(ResultSet rs,int rowNum) throws SQLException{
        /*Mobile mobile=new Mobile();
        mobile.setId(UUID.fromString(rs.getString("id")));
        mobile.setName(rs.getString("name"));
        mobile.setMadeIn(rs.getString("made_in"));
        mobile.setModel(rs.getString("model"));
        mobile.setYearOfManufacture(rs.getInt("year"));
        mobile.setUserId( UUID.fromString(rs.getString("user_id")) );*/
        if (rs.getString("user_id") == null) {
            return new Mobile();
        }

        return new Mobile(
                UUID.fromString(rs.getString("id")),
                rs.getString("name"),
                rs.getString("made_in"),
                rs.getString("model"),
                rs.getInt("year"),
                UUID.fromString(rs.getString("user_id"))
                );

    }
}

