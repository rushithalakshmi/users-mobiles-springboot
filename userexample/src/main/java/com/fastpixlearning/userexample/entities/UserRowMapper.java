package com.fastpixlearning.userexample.entities;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs,int rowNum) throws SQLException{
        return new User(
                UUID.fromString(rs.getString("id")),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender")
        );

    }
}