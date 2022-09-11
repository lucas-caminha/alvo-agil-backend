package br.com.ucsal.meta.agil.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${meta.jwt.expiration}")
	private String expiration;
	@Value("${meta.jwt.secret}")
	private String secret;
	
	public String getToken(Authentication authentication) {
		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
		Date now = new Date();
		Date dataExpiracao = new Date(now.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("META API")
				.setSubject(usuarioDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUserId(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return body.getSubject();
	}

	
}
