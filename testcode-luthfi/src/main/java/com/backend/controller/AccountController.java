package com.backend.controller;

import com.backend.dto.JwtResponseDTO;
import com.backend.dto.RegisterAccountDTO;
import com.backend.dto.RequestJwtDTO;
import com.backend.security.JwtManager;
import com.backend.service.AccountService;
import com.backend.service.AccountServiceImplement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);


    @PostMapping("/login")
    public ResponseEntity<Object> post( @RequestBody RequestJwtDTO dto) {
        try {
            var springSecurityToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(springSecurityToken);

            var userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            String jwtToken = jwtManager.generateToken(dto.getUsername(),
                    dto.getSecretKey(), dto.getSubject(), dto.getAudience());

            JwtResponseDTO responseDTO = new JwtResponseDTO(jwtToken, dto.getUsername());
            logger.info("Login successful for username: {}", dto.getUsername());
            return ResponseEntity.status(200).body(responseDTO);
        } catch (Exception e) {
            logger.error("Login failed for username: {}: {}", dto.getUsername(), e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterAccountDTO dto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                var registerAccountAdmin = service.register(dto);
                logger.info("Data berhasil disimpan");
                return ResponseEntity.status(HttpStatus.OK).body("Account Berhasil Register");
            }
            logger.info("binding result" + bindingResult.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
