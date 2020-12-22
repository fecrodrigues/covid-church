package br.com.fiap.covidchurch.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.covidchurch.auth.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Usuario findFirstByUserName(String userName);
}
