package org.dapamoha.api.controller;

import org.dapamoha.api.service.interf.UserService;
import org.dapamoha.shared.domain.dto.user.AuthTokenDto;
import org.dapamoha.shared.domain.dto.user.RequestEmailCodeResponseDto;
import org.dapamoha.shared.domain.dto.user.RequestEmailCodeDto;
import org.dapamoha.shared.domain.service.AuthenticationUser;
import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/requestEmailCode")
    public ResponseEntity<RequestEmailCodeResponseDto> sendCodeToEmail(
            @RequestBody RequestEmailCodeDto form
    ) {
        EmailToUserWithCode result = userService.requestEmailCode(form.getAddress());

        return new ResponseEntity<>(
                RequestEmailCodeResponseDto
                        .builder()
                        .id(result.getId())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/requestToken")
    public ResponseEntity<AuthTokenDto> getAuthTokenByEmailCode(
            @RequestParam Integer code,
            @RequestParam String address
    ) {

        AuthenticationUser result = userService.requestJwtToken(address, code);

        return new ResponseEntity<>(
                AuthTokenDto
                        .builder()
                        .token(result.getToken())
                        .tokenValidityInMs(result.getTokenValidityInMs())
                        .build(),
                HttpStatus.OK
        );
    }
}