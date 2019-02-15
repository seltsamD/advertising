package com.advertising.dashboard.service;

import com.advertising.dashboard.exception.AppNotFoundException;
import com.advertising.dashboard.model.dto.AppDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppService {
    AppDto findById(Integer id) throws AppNotFoundException;

    List<AppDto> findByUser(String email);

    List<AppDto> findAll();

    @Transactional
    AppDto save(AppDto dto);

    @Transactional
    AppDto update(AppDto dto);

    @Transactional
    void delete(Integer id);
}
