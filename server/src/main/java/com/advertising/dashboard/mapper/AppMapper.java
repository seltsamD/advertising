package com.advertising.dashboard.mapper;

import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.model.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppMapper {
    @Autowired
    private UserMapper userMapper;

    public AppDto mapToDto(App entity){
        AppDto dto = new AppDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setContentTypes(entity.getContentTypes());
        dto.setUser(userMapper.mapToDto(entity.getUser()));
        return dto;
    }

    public App mapToEntity(AppDto appDto){
        App entity = new App();
        entity.setId(appDto.getId());
        entity.setName(appDto.getName());
        entity.setType(appDto.getType());
        entity.setContentTypes(appDto.getContentTypes());
        return entity;
    }

}
