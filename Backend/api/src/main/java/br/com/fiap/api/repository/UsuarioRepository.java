package br.com.fiap.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.api.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	Usuario findFirstByUserName(String userName);
}
