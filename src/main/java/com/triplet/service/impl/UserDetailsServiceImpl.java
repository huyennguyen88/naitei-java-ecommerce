package com.triplet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.triplet.model.Role;
import com.triplet.model.User;

public class UserDetailsServiceImpl extends BaseServiceImpl implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = getUserDAO().findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("User " + username + " was not found in the database");
			}
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
			}
			MyUser myUser = new MyUser(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
			myUser.setId(user.getId());
			myUser.setFullname(user.getFullname());
			myUser.setEmail(user.getEmail());
			myUser.setAddress(user.getAddress());
			myUser.setAvatar(user.getAvatar());
			myUser.setPhone(user.getPhone());
			return myUser;
			
		}catch(Exception e){
			logger.error("have error in " + e);
			return null;
		}
	}

}
