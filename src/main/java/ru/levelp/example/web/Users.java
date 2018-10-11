package ru.levelp.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.levelp.example.dao.UsersDAO;
import ru.levelp.example.model.EventOwner;

import java.util.ArrayList;

@Service
public class Users implements UserDetailsService {
    @Autowired
    private UsersDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            String neverDoLikeThis = encoder.encode("admin");
            return new User(username, neverDoLikeThis, authorities);
        }

        ru.levelp.example.model.User user = dao.findByLogin(username);
        if (user != null) {
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            if (user instanceof EventOwner) {
                authorities.add(new SimpleGrantedAuthority("OWNER"));
            }

            return new User(username, user.getEncryptedPassword(), authorities);
        }

        throw new UsernameNotFoundException("User not found");
    }
}
