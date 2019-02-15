package com.advertising.dashboard.model.dto;

public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String userRole;
    private Boolean active = true;
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, String userRole, boolean isActive) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setUserRole(userRole);
        this.setActive(isActive);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userRole='" + userRole + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                '}';
    }
}
