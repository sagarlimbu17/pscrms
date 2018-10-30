package com.infodev.pscrms.dto;

import lombok.Data;

@Data
public class UserCreationMap {


    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String role;


}
