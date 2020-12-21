package br.com.fiap.covidchurch.auth.service;

import java.io.InvalidObjectException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.fiap.covidchurch.auth.dtos.JwtResponse;
import br.com.fiap.covidchurch.auth.exceptions.DuplicatedException;
import br.com.fiap.covidchurch.auth.exceptions.UnauthorizedException;
import br.com.fiap.covidchurch.auth.model.Credentials;
import br.com.fiap.covidchurch.auth.repository.CredentialsRepository;
import br.com.fiap.covidchurch.auth.security.JwtTokenTools;




@Service
@Component
public class LoginService {

	private final String MSG_ERRO_CRITERIO_USUARIO = "Critério para o id do usuário não foi atendido";
	private final String MSG_ERRO_CRITERIO_PWD = "Critério para senha não foi atendido";
	private final String MSG_ERRO_CREDENCIAIS_DUPLICADAS = "Já existe uma credencial para este usuário";
	private final String MSG_ERRO_CREDENCIAIS_NOT_FOUND = "Este usuário não possui cadastro";
	private final String DEFAULT_STATUS = "A";
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";



	private final MongoTemplate mongoTemplate;
	private final CredentialsRepository credentialsRepository;
	
	public LoginService(MongoTemplate mongoTemplate, CredentialsRepository credentialsRepository) {
		this.mongoTemplate = mongoTemplate;
		this.credentialsRepository = credentialsRepository;
	}

//	/* Metodo que efetua as validações basicas de um usuário e senha, 
//	 *  e insere as credenciais no banco de dados */
//	public Credentials insert(String userId, String pwd) throws DuplicatedException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidObjectException {
//
//		// Valida critérios básicos do id do usuário
//		if(!validarUsuario(userId)) {
//			throw new InvalidObjectException(MSG_ERRO_CRITERIO_USUARIO);
//		}
//		if(!validarSenha(pwd)) {
//			throw new InvalidObjectException(MSG_ERRO_CRITERIO_PWD);
//		}
//
//		// Verifica se já exite uma credencial para o usuário
//		Optional<Credentials> userFound = credentialsRepository.findOneByUserId(userId);
//		if(userFound.isPresent()) {
//			throw new DuplicatedException(MSG_ERRO_CREDENCIAIS_DUPLICADAS);
//		}
//
//		// Cria objeto e insere os dados da credencial
//		Credentials credentials = new Credentials();
//		credentials.setStatus(DEFAULT_STATUS);
//		credentials.setUserId(userId);
//
//		// efetua o hash do password
//		credentials.setPwd(hashPassword(pwd));
//
//		// Salva e retorna o objeto salvo
//		return credentialsRepository.insert(credentials);
//	}
//
//	/* Metodo que solicita reemissão da senha */
//	public String reset(String userId) throws DuplicatedException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidObjectException, NotFoundException {
//
//		Optional<Credentials> userFound = credentialsRepository.findOneByUserId(userId);
//		if(!userFound.isPresent()) {
//			throw new NotFoundException(MSG_ERRO_CREDENCIAIS_NOT_FOUND);
//		}
//
//		// Verifica se já exite uma credencial para o usuário
//		Query query = new Query(Criteria.where("_id").is(userFound.get().getId()));
//		Update update = new Update();
//
//		String tempPwd = generateRandomPassword();
//		update.set("pwdTemp", hashPassword(tempPwd));
//
//		System.out.println("tempPwd: " + tempPwd);
//		
//		// executes the query and updates the task
//		this.mongoTemplate.findAndModify(query, update, Credentials.class, "credenciais");
//		
//		//UserDto usuario = userService.getUsuarioPorId(userId);
//		
//		//emailService.enviarResetSenha(usuario.getEmail(), usuario.getName(), tempPwd);
//
//		return tempPwd;
//	}
//
//	/* Metodo que solicita reemissão da senha */
//	public boolean changePwd(String userId, String newPwd) throws DuplicatedException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidObjectException, NotFoundException {
//
//		Optional<Credentials> userFound = credentialsRepository.findOneByUserId(userId);
//		if(!userFound.isPresent()) {
//			throw new NotFoundException(MSG_ERRO_CREDENCIAIS_NOT_FOUND);
//		}
//
//		// Verifica se já exite uma credencial para o usuário
//		Query query = new Query(Criteria.where("_id").is(userFound.get().getId()));
//		Update update = new Update();
//
//		update.set("pwdTemp", null);
//		update.set("pwd", hashPassword(newPwd));
//
//		// executes the query and updates the task
//		this.mongoTemplate.findAndModify(query, update, Credentials.class, "credenciais");
//
//		return true;
//	}
//
//
//	public boolean clearTempPassword(String credId) throws DuplicatedException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidObjectException, NotFoundException {
//
//		// Verifica se já exite uma credencial para o usuário
//		Query query = new Query(Criteria.where("_id").is(credId));
//		Update update = new Update();
//
//		update.set("pwdTemp", null);
//
//		// executes the query and updates the task
//		this.mongoTemplate.findAndModify(query, update, Credentials.class, "credenciais");
//
//		return true;
//	}
	
	

	public JwtResponse autenticar(String userId, String pwd) throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException, DuplicatedException, InvalidObjectException {

		pwd = hashPassword(pwd);
		boolean isChangePass = false; 

		// Procura por usuário e senha na base de credenciais
		Optional<Credentials> userFound = credentialsRepository.findOneByUserIdAndPwd(userId, pwd);
		if(!userFound.isPresent()) {
			userFound = credentialsRepository.findOneByUserIdAndPwdTemp(userId, pwd);
			if(!userFound.isPresent()) {
				throw new UnauthorizedException("Usuário ou senha invalida");
			}
			isChangePass = true;
			userFound.get().setStatus("C");
		}
//		else {
//			clearTempPassword(userFound.get().getId());
//		}
		
		// gera e retorna token'''
		return new JwtResponse(JwtTokenTools.generateToken(userFound.get().getUserId(), userFound.get().getStatus(), isChangePass), userFound.get().getStatus());
	}

//	public JwtResponse autenticarService(String userId, String pwd) throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException, DuplicatedException, InvalidObjectException {
//
//		pwd = hashPassword(pwd);
//		boolean isChangePass = false; 
//
//		// gera e retorna token'''
//		return new JwtResponse(JwtTokenTools.generateToken(userId, "A", isChangePass), "A");
//	}
	
	

	private String hashPassword(String clearPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {

		int cost = 16;

		byte[] salt = new byte[16];

		salt = "Eng@$00720191234".getBytes();

		byte[] dk = pbkdf2(clearPassword.toCharArray(), salt, 1 << cost);
		byte[] hash = new byte[salt.length + dk.length];
		System.arraycopy(salt, 0, hash, 0, salt.length);
		System.arraycopy(dk, 0, hash, salt.length, dk.length);
		Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
		return enc.encodeToString(hash);

	}
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {

		String ALGORITHM = "PBKDF2WithHmacSHA1";
		int SIZE = 128;

		KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			return f.generateSecret(spec).getEncoded();
		}
		catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
		}
		catch (InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}

	private boolean validarSenha(String pwd) {
		return (
				pwd != null && 
				!pwd.isEmpty() && 
				pwd.length()>3);
	}
	private boolean validarUsuario(String user) {
		return (user != null && !user.isEmpty());
	}

	private String generateRandomPassword() {

		StringBuilder builder = new StringBuilder();

		int count = 5;
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}

		return builder.toString();	
	}


}
