package br.com.ucsal.meta.agil.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ucsal.meta.agil.entity.UsuarioEntity;
import br.com.ucsal.meta.agil.repository.UsuarioRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository userRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UsuarioRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		boolean isValid = tokenService.isTokenValid(token);
		
		if(isValid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);	
	}
	
	private void authenticateClient(String token) {
		String username = tokenService.getUserId(token);
		UsuarioEntity user = userRepository.findByUsername(username).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getPerfis());
		SecurityContextHolder.getContext().setAuthentication(authentication);	
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}


}
