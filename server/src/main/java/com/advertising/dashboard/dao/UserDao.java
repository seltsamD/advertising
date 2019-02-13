package com.advertising.dashboard.dao;

import com.advertising.dashboard.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
