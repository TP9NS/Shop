package com.example.server.DTOREUQUEST;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerLoginRequest {
    private String username;
    private String password;
}