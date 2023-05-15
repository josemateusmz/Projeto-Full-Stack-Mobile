package com.gofinancas.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String descricao;

    private LocalDate accountCreationDate;

}
