package com.example.nnpiacv02.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "task")
public class Task {

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private AppUser appUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Date creationDate;

    private Date updateDate;
}
