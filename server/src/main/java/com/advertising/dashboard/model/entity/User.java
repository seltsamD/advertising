package com.advertising.dashboard.model.entity;

import com.advertising.dashboard.model.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "UserCredentials")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @Size(min = 1, max = 255)
    private String name;

    @Column
    @Size(min = 3, max = 255)
    private String email;

    @Column
    @Size(min = 1, max = 255)
    private String password;

    @Column
    private String userRole;

    @Column
    private Boolean active = true;

    public User() {
    }

    public User(@Size(min = 1, max = 255) String name, @Size(min = 3, max = 255) String email, @Size(min = 1, max = 255) String password, String userRole, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.active = active;
    }

    public Integer getId() {
        return id;
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

    public UserRole getUserRole() {
        return UserRole.valueOf(userRole);
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole.toString();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                userRole.equals(user.userRole) &&
                active.equals(user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, userRole, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
