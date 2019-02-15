package com.advertising.dashboard.service.impl;

import com.advertising.dashboard.dao.AppDao;
import com.advertising.dashboard.dao.UserDao;
import com.advertising.dashboard.exception.AppNotFoundException;
import com.advertising.dashboard.mapper.AppMapper;
import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.model.entity.App;
import com.advertising.dashboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppMapper appMapper;

    @Override
    public AppDto findById(Integer id) throws AppNotFoundException {
        Optional<App> optionalApp = appDao.findById(id);
        if (optionalApp.isPresent()) {
            return appMapper.mapToDto(optionalApp.get());
        } else {
            throw new AppNotFoundException(String.format("Couldn't find app with id %d", id));
        }
    }

    @Override
    public List<AppDto> findByUser(String email) {
        return appDao.findAppsByUserEmail(email).stream()
                .map(app -> appMapper.mapToDto(app))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppDto> findAll() {
        return appDao.findAll().stream()
                .map(app -> appMapper.mapToDto(app))
                .collect(Collectors.toList());
    }

    @Override
    public AppDto save(AppDto dto) {
        App app = appMapper.mapToEntity(dto);
        app.setUser(userDao.findById(dto.getUser().getId()).get());
        return appMapper.mapToDto(appDao.save(app));
    }

    @Override
    public AppDto update(AppDto dto) {
        Optional<App> appOptional = appDao.findById(dto.getId());
        App app;
        if (appOptional.isPresent()) {
            app = appOptional.get();
            app.setContentTypes(dto.getContentTypes());
            app.setType(dto.getType());
            app.setName(dto.getName());
            app.setUser(userDao.findById(dto.getUser().getId()).get());
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
