package com.startup.vanguard.dto.auth;

public record AuthenticationRequest (
        String username,
        String password
){
}