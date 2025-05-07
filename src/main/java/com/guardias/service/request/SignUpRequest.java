package com.guardias.service.request;


import com.guardias.persintence.entity.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a request for signing up a new user.
 */
@Data
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String das;
    private String password;
    private String email;
    private Roles role;
}