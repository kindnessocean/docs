package org.dapamoha.api.service.impl;

import org.dapamoha.api.security.jwt.JwtTokenProvider;
import org.dapamoha.api.service.interf.AuthService;
import org.dapamoha.shared.domain.service.AuthenticationUser;
import org.dapamoha.shared.posgresql.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    @Value("${org.dapamoha.api.auth.token.validityTime}")
    private Long validityTime;

    public AuthServiceImpl(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthenticationUser auth(final User user) {
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());

        return AuthenticationUser.builder()
                .tokenValidityInMs(validityTime)
                .token(token)
                .build();
    }
}
