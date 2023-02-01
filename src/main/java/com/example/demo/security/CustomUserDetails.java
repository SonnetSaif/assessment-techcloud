package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails {
    private String name;
    private String id;
    private String validated;
}
