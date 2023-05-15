package com.gofinancas.Service;

import com.gofinancas.domain.dto.UsuarioDto;
import com.gofinancas.domain.exception.TrataException;
import com.gofinancas.domain.model.UsuarioModel;
import com.gofinancas.domain.request.UsuarioRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gofinancas.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioRequest salvar(UsuarioDto usuarioDto){

        boolean emailEmUso = usuarioRepository.findByEmail(usuarioDto.getEmail()).stream().anyMatch(c -> !c.equals(usuarioDto));

        if (emailEmUso){
            throw new TrataException("Já existe um usuário cadastrado com este e-mail.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        validaSenha(usuarioDto.getSenha1(), usuarioDto.getSenha2());

        String senhaCriptografada1 = encoder.encode(usuarioDto.getSenha1());

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setEmail(usuarioDto.getEmail());
        usuarioModel.setNome(usuarioDto.getNome());
        usuarioModel.setSenha1(senhaCriptografada1);
        usuarioModel.setTelefone(usuarioDto.getTelefone());
        usuarioModel.setDescricao(usuarioDto.getDescricao());
        usuarioModel.setAccountCreationDate(LocalDate.now());

        this.usuarioRepository.save(usuarioModel);

        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail(usuarioModel.getEmail());
        usuarioRequest.setNome(usuarioModel.getNome());
        usuarioRequest.setId(usuarioModel.getId());
        usuarioRequest.setDescricao(usuarioModel.getDescricao());
        usuarioRequest.setTelefone(usuarioModel.getTelefone());
        usuarioRequest.setAccountCreationDate(usuarioModel.getAccountCreationDate());

        return usuarioRequest;
    }

    private void validaSenha(String senha1, String senha2){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!senha1.equals(senha1)){
            throw new TrataException("Senhas não são iguais. As senhas precisam ser iguais.");
        }
    }

    public Optional<UsuarioModel> buscarUsuario(String email){
        Optional<UsuarioModel> usuarioModel = Optional.of(new UsuarioModel());
        if (!usuarioRepository.existsByEmail(email)){
            throw new TrataException("Usuário não cadastro");
        }else {
            usuarioModel = usuarioRepository.findByEmail(email);
            return usuarioModel;
        }
    }

}
