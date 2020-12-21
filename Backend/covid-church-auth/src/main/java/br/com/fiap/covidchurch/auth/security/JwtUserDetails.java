package br.com.fiap.covidchurch.auth.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.fiap.covidchurch.auth.entity.Usuario;
import br.com.fiap.covidchurch.auth.repository.UsuarioRepository;

@Component
public class JwtUserDetails implements UserDetailsService{

    private UsuarioRepository userRepository;

    public JwtUserDetails(UsuarioRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario user = userRepository.findFirstByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("usuario.nao.encontrado");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>() // roles
        );
    }
}
