package br.com.fiap.covidchurch.auth.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.covidchurch.auth.dtos.DefaultHttpJsonResponse;
import br.com.fiap.covidchurch.auth.dtos.JwtRequest;
import br.com.fiap.covidchurch.auth.dtos.JwtResponse;
import br.com.fiap.covidchurch.auth.security.RequestTools;
import br.com.fiap.covidchurch.auth.service.LoginService;
import br.com.fiap.covidchurch.auth.service.SecurityService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;
	private SecurityService securityService;
	
	public LoginController(LoginService loginService, SecurityService securityService) {
		this.loginService = loginService;
		this.securityService = securityService;
	}

	@PostMapping()
	public DefaultHttpJsonResponse<JwtResponse> createAuthenticationToken(HttpServletRequest request) throws Exception {

		JwtRequest basicInfo = RequestTools.getBasicInfo(request);

		final JwtResponse token = loginService.autenticar(basicInfo.getUsername(), basicInfo.getPassword());
		
		return new DefaultHttpJsonResponse<JwtResponse>(token);
	}

	@GetMapping()
	public ResponseEntity<DefaultHttpJsonResponse<String>> checkAuthenticationToken(HttpServletRequest request) throws Exception {

		//Cria um obj json
		JSONObject jo = new JSONObject();
		//Adiciona o userid no objeto
		jo.append("userId", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		// Cria um objeto calendário com a data de expiração do token formatado
		SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date date=securityService.getExpTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Adiciona a data de expiração formatada no objeto
		jo.append("ext", format.format(cal.getTime()));

		jo.append("status", securityService.getStatus());

		//Retorna http200 com o objeto json criado com dados do token
		return ResponseEntity.ok(new DefaultHttpJsonResponse<String>(jo.toString()));

	}


}
