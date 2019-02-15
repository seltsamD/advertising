package com.advertising.dashboard.model.dto;

import com.advertising.dashboard.model.AppType;

public class AppDtoRequest {
    Integer id;
    String name;
    AppType type;
    Object contentTypes;
    UserDto user;

    public AppDtoRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppType getType() {
        return type;
    }

    public void setType(AppType type) {
        this.type = type;
    }

    public Object getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Object contentTypes) {
        this.contentTypes = contentTypes;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
