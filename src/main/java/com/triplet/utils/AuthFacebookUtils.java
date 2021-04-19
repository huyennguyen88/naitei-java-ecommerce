package com.triplet.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.triplet.model.Role;
import com.triplet.model.User;
import com.triplet.service.impl.MyUser;

public class AuthFacebookUtils {
	
	public MyUser SetAuthentication(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
		}
		MyUser myUser = new MyUser(user.getUsername(), "", true, true, true, true, authorities);
		myUser.setId(user.getId());
		myUser.setAvatar(user.getAvatar());
		myUser.setEmail(user.getEmail());
		myUser.setFullname(user.getFullname());
		myUser.setAddress(user.getAddress());
		myUser.setPhone(user.getPhone());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(myUser, null,
				myUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return myUser;
	}

}
