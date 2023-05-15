package com.gofinancas.Service;

import com.gofinancas.domain.dto.LoginDto;
import com.gofinancas.domain.exception.TrataException;
import com.gofinancas.domain.model.LoginModel;
import com.gofinancas.domain.model.UsuarioModel;
import com.gofinancas.domain.request.LoginRequest;
import com.gofinancas.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private LoginRepository loginRepository;
    private UsuarioService usuarioService;

    @Transactional
    public LoginRequest login(LoginDto loginDto){
        Optional<UsuarioModel> usuarioModel = usuarioService.buscarUsuario(loginDto.getEmail());

        LoginModel loginModel = new LoginModel();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        validaSenha(loginDto.getSenha(), usuarioModel.get().getSenha1());

        loginModel.setEmail(usuarioModel.get().getEmail());
        loginModel.setIdUsuario(usuarioModel.get().getId());
        loginRepository.save(loginModel);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(loginModel.getEmail());
        loginRequest.setIdUsuario(loginModel.getIdUsuario());

        return loginRequest;

    }

    private void validaSenha(String senha, String senhaBanco){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(senha, senhaBanco)){
            throw  new TrataException("Senha inválida");
        }
    }
}
