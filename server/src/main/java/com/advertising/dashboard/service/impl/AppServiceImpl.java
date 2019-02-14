package com.advertising.dashboard.service.impl;

import com.advertising.dashboard.dao.AppDao;
import com.advertising.dashboard.exception.AppNotFoundException;
import com.advertising.dashboard.mapper.AppMapper;
import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.model.entity.App;
import com.advertising.dashboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    @Autowired
    private AppMapper appMapper;

    @Override
    public AppDto findById(Integer id) throws AppNotFoundException {
        Optional<App> optionalApp = appDao.findById(id);
        if(optionalApp.isPresent()) {
            return appMapper.mapToDto(optionalApp.get());
        } else {
            throw new AppNotFoundException(String.format("Couldn't find app with id %d", id));
        }
    }

    @Override
    public List<AppDto> findByUser(Integer userId) {
        return appMapper.mapToDtoList(appDao.findAppsByUser(userId));
    }

    @Override
    public List<AppDto> findAll() {
        return appMapper.mapToDtoList(appDao.findAll());
    }

    @Override
    public AppDto saveOrUpdate(AppDto dto) {
        Optional<App> appOptional = appDao.findById(dto.getId());
        App app;
        if(appOptional.isPresent()) {
            app = appOptional.get();
            app.setContentTypes(dto.getContentTypes());
            app.setType(dto.getType());
            app.setName(dto.getName());
        } else {
            app = appMapper.mapToEntity(dto);
        }
        return appMapper.mapToDto(appDao.save(app));
    }

    @Override
    public void delete(Integer id) {
        appDao.deleteById(id);
    }
}
