package br.com.ucsal.meta.agil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.dto.UsuarioDTO;
import br.com.ucsal.meta.agil.entity.UsuarioEntity;
import br.com.ucsal.meta.agil.exception.UserExistingException;
import br.com.ucsal.meta.agil.repository.UsuarioRepository;
import br.com.ucsal.meta.agil.security.UsuarioDetails;
import br.com.ucsal.meta.agil.util.MessageUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioEntity> findByUsername = userRepository.findByUsername(username);		
		if (findByUsername.isPresent()) {
			return new UsuarioDetails(findByUsername.get());
		}		
		throw new UsernameNotFoundException(username);
	}
	
	public UserDetails registerNewUserAccount(UsuarioDTO usuario) {
		
		Optional<UsuarioEntity> find = userRepository.findByUsername(usuario.getNome());
			
		if (find.isEmpty()) {
			UsuarioEntity entity = new UsuarioEntity();
			entity.setUsername(usuario.getNome());
			entity.setPassword(passwordEncoder.encode(usuario.getSenha()));
			entity.setEmail(usuario.getSenha());
			UsuarioEntity saved = userRepository.save(entity);
			return new UsuarioDetails(saved);
		}
		
		throw new UserExistingException(MessageUtil.USUARIO_EXISTENTE);
	}

}
