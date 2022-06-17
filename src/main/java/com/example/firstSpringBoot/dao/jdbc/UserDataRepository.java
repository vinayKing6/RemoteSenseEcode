package com.example.firstSpringBoot.dao.jdbc;

import com.example.firstSpringBoot.dao.User;

public interface UserDataRepository {

    Iterable<User> findAll();

    User findOne(String name);

    User save(User user);
}
