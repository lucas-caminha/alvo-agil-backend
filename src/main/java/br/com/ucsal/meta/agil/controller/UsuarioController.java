package br.com.ucsal.meta.agil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.meta.agil.dto.UsuarioDTO;
import br.com.ucsal.meta.agil.exception.UserExistingException;
import br.com.ucsal.meta.agil.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private CustomUserDetailsService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/registrar")
	public ResponseEntity<UserDetails> register(@RequestBody UsuarioDTO usuario){
		try {
			UserDetails registred = userService.registerNewUserAccount(usuario);
			if (registred != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(registred);
			}
		} catch (UserExistingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
	
}
