package com.productiontracking.dto.request;

import lombok.Data;

@Data
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String role;

    public CreateUserDto(String firstName, String lastName, String email, String phone, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public CreateUserDto() {
        super();
    }
}
