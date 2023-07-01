package com.Ryoshi.DatabaseProgram.service;

import com.Ryoshi.DatabaseProgram.config.UserToUserDetails;
import com.Ryoshi.DatabaseProgram.model.User;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(UserToUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found" + username));

    }

}
