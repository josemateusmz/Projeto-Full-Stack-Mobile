package com.gofinancas.api.controller;

import com.gofinancas.Service.LoginService;
import com.gofinancas.domain.dto.LoginDto;
import com.gofinancas.domain.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginRequest logar(@Valid @RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }
}
