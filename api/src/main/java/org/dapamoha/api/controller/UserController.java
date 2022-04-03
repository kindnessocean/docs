package org.dapamoha.api.controller;

import org.dapamoha.api.service.interf.UserService;
import org.dapamoha.shared.domain.dto.user.ExistUserByUsernameDto;
import org.dapamoha.shared.domain.dto.user.PrivateUserDto;
import org.dapamoha.shared.domain.dto.user.UserUpdateForm;
import org.dapamoha.shared.posgresql.entity.User;
import org.dapamoha.shared.posgresql.util.ConverterSqlEntityToDtoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PrivateUserDto> getUserProfile(
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        User user = userService.getUserByUserDetails(userDetails);

        return new ResponseEntity<PrivateUserDto>(
                ConverterSqlEntityToDtoUtil.convert(user),
                HttpStatus.OK);
    }

    @GetMapping("/isExistUserByUsername")
    public ResponseEntity<ExistUserByUsernameDto> isExistUserByUsername(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String username
    ) {

        Boolean isExist = userService.isExistUserByUsername(username);

        return new ResponseEntity<ExistUserByUsernameDto>(
                ExistUserByUsernameDto.builder()
                        .isExist(isExist)
                        .build(),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PrivateUserDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateForm userUpdateForm){

        User user = userService.update(userDetails, userUpdateForm);

        return new ResponseEntity<PrivateUserDto>(
                ConverterSqlEntityToDtoUtil.convert(user),
                HttpStatus.OK);
    }
}