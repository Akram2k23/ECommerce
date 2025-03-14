package com.ecom.userdetailsservice;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entities.Ent_User;
import com.ecom.repo.UserRepository;


public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Ent_User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
                .collect(Collectors.toSet());
        
        System.out.println("User-detais-service");
        System.out.println(user.getUsername()+"==="+user.getPassword());
        System.out.println("user : "+user.getUsername()+"||"+"password : "+user.getPassword()+"||"+"Role : "+authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        		authorities);
        
//                new ArrayList<>()); // You might need to map roles/authorities here
	}

}
