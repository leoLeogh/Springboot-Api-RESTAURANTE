package com.restaurante.app.serviceImplement;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.restaurante.app.model.Administrador;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public class UserDetailImplement implements UserDetails{

	private final Administrador administrador;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return administrador.getClave();
	}

	@Override
	public String getUsername() {
		return administrador.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUser() {
		return administrador.getUsuario();
	}
	
}
