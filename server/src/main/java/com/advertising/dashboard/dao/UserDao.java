package com.advertising.dashboard.dao;

import com.advertising.dashboard.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    List<User> findAllByActiveTrue();

    List<User> findByUserRoleIn(Set<String> userRoles);


}
