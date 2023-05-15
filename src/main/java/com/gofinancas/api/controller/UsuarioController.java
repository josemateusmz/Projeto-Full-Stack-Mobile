package com.gofinancas.api.controller;

import com.gofinancas.Service.UsuarioService;
import com.gofinancas.domain.dto.UsuarioDto;
import com.gofinancas.domain.model.UsuarioModel;
import com.gofinancas.domain.request.UsuarioRequest;
import com.gofinancas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/userRegistration")
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRequest cadastrar(@Valid @RequestBody UsuarioDto usuarioDto){
        return usuarioService.salvar(usuarioDto);
    }

    @GetMapping("/{id}")
    public UsuarioRequest getUsuario(@PathVariable("id") Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        UsuarioRequest usuarioRequest = new UsuarioRequest();

        usuarioRequest.setEmail(usuarioModel.get().getEmail());
        usuarioRequest.setId(usuarioModel.get().getId());
        usuarioRequest.setNome(usuarioModel.get().getNome());
        usuarioRequest.setTelefone(usuarioModel.get().getTelefone());
        usuarioRequest.setDescricao(usuarioModel.get().getDescricao());
        usuarioRequest.setAccountCreationDate(usuarioModel.get().getAccountCreationDate());

        return usuarioRequest;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRequest atualizarUsuario(@Valid @PathVariable Long id, @RequestBody UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioModel = usuarioService.buscarUsuario(usuarioDto.getEmail());

        usuarioModel.get().setDescricao(usuarioDto.getDescricao());
        usuarioModel.get().setTelefone(usuarioDto.getTelefone());
        usuarioModel.get().setNome(usuarioDto.getNome());

        usuarioRepository.save(usuarioModel.get());

        UsuarioRequest usuarioRequest = new UsuarioRequest();

        usuarioRequest.setEmail(usuarioModel.get().getEmail());
        usuarioRequest.setId(usuarioModel.get().getId());
        usuarioRequest.setNome(usuarioModel.get().getNome());
        usuarioRequest.setTelefone(usuarioDto.getTelefone());
        usuarioRequest.setDescricao(usuarioDto.getDescricao());
        usuarioRequest.setAccountCreationDate(usuarioModel.get().getAccountCreationDate());

        return usuarioRequest;

    }
    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id){
        usuarioRepository.deleteById(id);
    }


}
