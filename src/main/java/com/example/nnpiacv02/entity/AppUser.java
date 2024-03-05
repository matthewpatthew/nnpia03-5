package com.example.nnpiacv02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//create, update, validate, create-drop, none
@Data
@Entity(name = "app_user")
@NoArgsConstructor
public class AppUser {

    @ManyToMany
    @JoinTable(name = "app_user_role",joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "appUser")
    private List<Task> tasks  = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean active;

    private Date creationDate;

    private Date updateDate;

    public AppUser(String username, String password, boolean active, Date creationDate, Date updateDate) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
