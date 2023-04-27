package com.cloud.taco.security;

import com.cloud.taco.User;
import com.cloud.taco.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    This configuration defines a UserDetailsService bean which is used by Spring Security
//    for user authentication and authorization. It takes a UserRepository object as a parameter,
//    which is used to retrieve the user information from a database or other data store.
//    The UserDetailsService is responsible for loading user-specific data,
//    such as the user's authorities and password, and returning a UserDetails object,
//    which is used by Spring Security to perform authentication and authorization checks.
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '"+username+"' not found");
        };
    }

}
