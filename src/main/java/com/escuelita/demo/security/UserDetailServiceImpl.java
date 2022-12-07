package com.escuelita.demo.security;

import com.escuelita.demo.entities.User;
import com.escuelita.demo.entities.UserAdmin;
import com.escuelita.demo.repositories.IUserRepository;
import com.escuelita.demo.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserAdminRepository userAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAdmin userAdmin =userAdminRepository.findOneByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con email"+ email + "no existe"));
            return new UserDetailsImpl(userAdmin);
    }
}
