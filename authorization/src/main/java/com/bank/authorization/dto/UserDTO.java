package com.bank.authorization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String role;
    private int profileId;
    private String password;
}