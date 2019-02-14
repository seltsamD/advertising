package com.advertising.dashboard.dao;

import com.advertising.dashboard.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByActiveTrue();
}
