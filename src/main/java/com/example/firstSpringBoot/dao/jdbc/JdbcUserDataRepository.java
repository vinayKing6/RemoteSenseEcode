package com.example.firstSpringBoot.dao.jdbc;

import com.example.firstSpringBoot.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserDataRepository implements UserDataRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUserDataRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Iterable<User> findAll() {
        return jdbc.query("select user_id,user_name,user_email,user_password from user",
                this::mapRowToUserData);
    }

    @Override
    public User findOne(String name) {
        try {
            return jdbc.queryForObject("select user_id,user_name,user_email,user_password from user where user_name=?",
                    this::mapRowToUserData,name);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public User save(User user) {
        jdbc.update("insert into user (user_name,user_email,user_password) values (?,?,?)",
                user.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    private User mapRowToUserData(ResultSet rs, int rowNum) throws SQLException {
//        return new UserData();
        return null;
    }
}
