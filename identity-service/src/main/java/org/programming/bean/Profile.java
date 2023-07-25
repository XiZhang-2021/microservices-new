package org.programming.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "MYPROFILE")
public class Profile implements GrantedAuthority {

    @Id
    private int id;

    @Column
    private String type;


    public Profile(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return type;
    }
}
