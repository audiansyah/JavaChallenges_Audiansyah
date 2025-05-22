package com.eshopper_backend_final.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersDto {
    private Long userId;
    
    private String userName;

    private String userEmail;

    private String userPassword;

    private String userHandphone;
}
