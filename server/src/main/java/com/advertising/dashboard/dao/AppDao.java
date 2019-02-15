package com.advertising.dashboard.dao;

import com.advertising.dashboard.model.entity.App;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppDao extends CrudRepository<App, Integer> {
    List<App> findAppsByUserEmail(String email);
    List<App> findAll();
}
