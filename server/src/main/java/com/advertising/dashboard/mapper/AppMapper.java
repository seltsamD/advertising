package com.advertising.dashboard.mapper;

import com.advertising.dashboard.model.dto.AppDto;
import com.advertising.dashboard.model.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        dto.setUserDto(userMapper.maptoDto(entity.getUser()));
        return dto;
    }

    public App mapToEntity(AppDto appDto){
        App entity = new App();
        entity.setId(appDto.getId());
        entity.setName(appDto.getName());
        entity.setType(appDto.getType());
        entity.setContentTypes(appDto.getContentTypes());
        entity.setUser(userMapper.mapToEntity(appDto.getUserDto()));
        return entity;
    }

    public List<AppDto> mapToDtoList(List<App> entityList){
        return entityList.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
