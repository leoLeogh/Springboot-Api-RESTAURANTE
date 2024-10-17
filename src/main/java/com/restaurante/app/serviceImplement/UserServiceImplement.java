package com.restaurante.app.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restaurante.app.model.Administrador;
import com.restaurante.app.repository.AdministradorRepository;

@Service
public class UserServiceImplement implements UserDetailsService{

	@Autowired
	private AdministradorRepository dao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Administrador administrador = dao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: "+email+ "no existe."));
		
		return new UserDetailImplement(administrador);
	}
		
}
