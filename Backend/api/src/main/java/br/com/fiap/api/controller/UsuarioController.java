package br.com.fiap.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api.dto.AuthDTO;
import br.com.fiap.api.dto.CreateUserDTO;
import br.com.fiap.api.dto.JwtDTO;
import br.com.fiap.api.dto.UserDTO;
import br.com.fiap.api.service.UsuarioService;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	private UsuarioService userService;

    public UsuarioController(UsuarioService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody CreateUserDTO userCreateDTO){
        return userService.create(userCreateDTO);
    }

    @PostMapping("login")
    public JwtDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }
}
