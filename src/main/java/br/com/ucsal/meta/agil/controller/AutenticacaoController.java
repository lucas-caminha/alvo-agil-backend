package br.com.ucsal.meta.agil.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.meta.agil.dto.TokenDTO;
import br.com.ucsal.meta.agil.dto.UsuarioDTO;
import br.com.ucsal.meta.agil.security.TokenService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody @Valid UsuarioDTO usuario){
		UsernamePasswordAuthenticationToken login = usuario.toToken();
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.getToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}		
	}
	
}
