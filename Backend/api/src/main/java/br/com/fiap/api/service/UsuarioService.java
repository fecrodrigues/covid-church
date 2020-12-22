package br.com.fiap.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.api.dto.AuthDTO;
import br.com.fiap.api.dto.CreateUserDTO;
import br.com.fiap.api.dto.JwtDTO;
import br.com.fiap.api.dto.UserDTO;
import br.com.fiap.api.model.Usuario;
import br.com.fiap.api.repository.UsuarioRepository;
import br.com.fiap.api.security.JwtToken;

@Service
public class UsuarioService {

	private final AuthenticationManager authenticationManager;
    private final JwtToken jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;

    public UsuarioService(AuthenticationManager authenticationManager,
                           JwtToken jwtTokenUtil,
                           PasswordEncoder passwordEncoder,
                           UsuarioRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDTO create(CreateUserDTO userCreateDTO) {
        Usuario user = new Usuario();
        user.setUserName(userCreateDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        Usuario savedUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(savedUser.getUserName());

        return userDTO;
    }

    public JwtDTO login(AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUserName(), authDTO.getPassword()));
        } catch (DisabledException disabledException){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuario.desabilitado");
        } catch (BadCredentialsException badCredentialsException){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "credenciais.invalidas");
        }
        Usuario usuario = userRepository.findFirstByUserName(authDTO.getUserName());
        
        String token = jwtTokenUtil.generateToken(authDTO.getUserName(), usuario.getCpf());

        JwtDTO tokenDTO = new JwtDTO();
        tokenDTO.setToken(token);
        return tokenDTO;
    }
}
