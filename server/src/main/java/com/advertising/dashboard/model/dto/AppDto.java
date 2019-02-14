package com.advertising.dashboard.model.dto;

import com.advertising.dashboard.model.AppType;
import com.advertising.dashboard.model.ContentType;

import java.util.Set;

public class AppDto {
    Integer id;
    String name;
    AppType type;
    Set<ContentType> contentTypes;
    UserDto user;

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

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
