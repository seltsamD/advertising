package com.advertising.dashboard.model.entity;

import com.advertising.dashboard.model.AppType;
import com.advertising.dashboard.model.ContentType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class App implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Integer id;

    @Column
    @Size(min = 1, max = 255)
    private String name;

    @Column
    private AppType type;

    @Column
    @ElementCollection(targetClass=ContentType.class)
    private Set<ContentType> contentTypes;

    @OneToOne
    private User user;

    public App() {
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

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        App app = (App) o;

        if (!id.equals(app.id)) return false;
        if (!name.equals(app.name)) return false;
        if (type != app.type) return false;
        if (!contentTypes.equals(app.contentTypes)) return false;
        return user.equals(app.user);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + contentTypes.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
