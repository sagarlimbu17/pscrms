package com.infodev.pscrms.dto;

import lombok.Data;

@Data
public class UserUpdateMapper {

    private Long id;

    private String username;

    private String firstName;
    private String middleName;

    private String lastName;

    private String email;



}
