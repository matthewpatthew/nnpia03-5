package com.example.nnpiacv02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AppUserDto {

    private Long id;
    private String username;
    private String password;
    private boolean active;
    private Date creationDate;
    private Date updateDate;
}
