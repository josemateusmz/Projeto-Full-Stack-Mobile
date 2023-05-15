package com.gofinancas.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    private String nome;
    private String email;
    private String senha1;
    private String senha2;
    private String telefone;
    private String descricao;

}
