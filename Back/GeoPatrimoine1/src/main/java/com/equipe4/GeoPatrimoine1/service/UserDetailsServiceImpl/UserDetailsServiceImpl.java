package com.equipe4.GeoPatrimoine1.service.UserDetailsServiceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.equipe4.GeoPatrimoine1.entity.Utilisateur;
import com.equipe4.GeoPatrimoine1.service.UtilisateurService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		final Utilisateur utilisateur = utilisateurService.findByLogin(login);
		if (utilisateur == null) {
			LOGGER.warn("Utilisateur {} was not found in the database", utilisateur);
			
			return null;
		} else {
			LOGGER.debug("Utilisateur {} was found in the database", utilisateur);

			final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			// final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userFromDatabase.getRole());
			// grantedAuthorities.add(grantedAuthority);

			return new User(login, "", grantedAuthorities);
		}
	}
}