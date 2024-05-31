package com.backend.service;

import com.backend.dto.RegisterAccountDTO;
import com.backend.entity.Account;
import com.backend.repository.AccountRepository;
import com.backend.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplement implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = repository.findById(username).get();
        return new ApplicationUserDetails(entity);
    }

    @Override
    public Object register(RegisterAccountDTO dto) {
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        var entity = new Account(
                dto.getUsername(),
                hashedPassword
        );
        return repository.save(entity);
    }
}
