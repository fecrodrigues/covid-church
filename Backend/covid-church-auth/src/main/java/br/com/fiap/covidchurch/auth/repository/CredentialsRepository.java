package br.com.fiap.covidchurch.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.covidchurch.auth.model.Credentials;

@Repository
public interface CredentialsRepository extends MongoRepository<Credentials, String>{

	Optional<Credentials> findOneByUserIdAndPwd(String userId, String pwd);
	Optional<Credentials> findOneByUserIdAndPwdTemp(String userId, String pwd);
	Optional<Credentials> findOneByUserId(String userId);

}
