package com.cyphersoft.osahaneat.security;

import com.cyphersoft.osahaneat.entity.Users;
import com.cyphersoft.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Kiểm tra:" +username);
        Users users = userRepository.findByUserName(username);

        if(users == null){
            throw new UsernameNotFoundException("User can't exits");
        }
        return new User(username, users.getPassword(), new ArrayList<>());
    }
}
