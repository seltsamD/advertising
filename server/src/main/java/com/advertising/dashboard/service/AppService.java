package com.advertising.dashboard.service;

import com.advertising.dashboard.exception.AppNotFoundException;
import com.advertising.dashboard.model.dto.AppDto;

import java.util.List;

public interface AppService {
    AppDto findById(Integer id) throws AppNotFoundException;
    List<AppDto> findByUser(Integer userId);
    List<AppDto> findAll();
    AppDto saveOrUpdate(AppDto dto);
    void delete(Integer id);
}
