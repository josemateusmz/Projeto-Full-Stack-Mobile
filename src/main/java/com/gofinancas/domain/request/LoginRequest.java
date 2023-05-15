package com.gofinancas.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private Long idUsuario;

    private String email;
}
