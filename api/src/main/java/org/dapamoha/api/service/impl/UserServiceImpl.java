package org.dapamoha.api.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.dapamoha.api.service.interf.AuthService;
import org.dapamoha.api.service.interf.UserService;
import org.dapamoha.api.service.mq.producer.EmailRequestProducer;
import org.dapamoha.shared.domain.dto.user.RequestEmailCodeDto;
import org.dapamoha.shared.domain.dto.user.UserUpdateForm;
import org.dapamoha.shared.domain.exception.IllegalArgAppException;
import org.dapamoha.shared.domain.service.AuthenticationUser;
import org.dapamoha.shared.domain.util.CodeUtil;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestKey;
import org.dapamoha.shared.kafka.model.emailRequest.EmailRequestValue;
import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;
import org.dapamoha.shared.mongo.repository.letterToUserWithCodeToEmail.EmailToUserWithCodeRepository;
import org.dapamoha.shared.posgresql.entity.Role;
import org.dapamoha.shared.posgresql.entity.RoleType;
import org.dapamoha.shared.posgresql.entity.User;
import org.dapamoha.shared.posgresql.repository.role.RoleRepository;
import org.dapamoha.shared.posgresql.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final EmailToUserWithCodeRepository emailToUserWithCodeRepository;
    private final AuthService authService;
    private final EmailRequestProducer emailRequestProducer;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${org.dapamoha.api.code.expiredTime}")
    private Integer expiredTime;

    public UserServiceImpl(
            final EmailToUserWithCodeRepository emailToUserWithCodeRepository,
            final EmailRequestProducer emailRequestProducer,
            final UserRepository userRepository,
            final AuthService authService, final RoleRepository roleRepository) {
        this.emailToUserWithCodeRepository = emailToUserWithCodeRepository;
        this.emailRequestProducer = emailRequestProducer;
        this.userRepository = userRepository;
        this.authService = authService;
        this.roleRepository = roleRepository;
    }

    @Override
    public EmailToUserWithCode requestEmailCode(final String address) {
        UUID uuid = UUID.randomUUID();
        Date created = new Date();
        Date expiredAt = new Date(created.getTime() + expiredTime);

        // Delete all letter to this email address
        emailToUserWithCodeRepository.deleteAllByAddress(address);

        EmailToUserWithCode letter = emailToUserWithCodeRepository.save(
                EmailToUserWithCode.builder()
                        .code(CodeUtil.generateCodeAsNumber())
                        .uuid(uuid.toString())
                        .address(address)
                        .created(created)
                        .expiredAt(expiredAt)
                        .build()
        );

        emailRequestProducer.produceRequest(
                new EmailRequestKey(uuid),
                new EmailRequestValue(
                        address,
                        letter.getCode()
                )
        );

        return letter;
    }

    @Override
    public AuthenticationUser requestJwtToken(final String emailAddress, final Integer code) {
        EmailToUserWithCode emailLetter = emailToUserWithCodeRepository.getByAddress(emailAddress);

        if (emailLetter == null || !Objects.equals(emailLetter.getCode(), code)) {
            throw new IllegalArgAppException("That code " + code + " didn't send to " + emailAddress);
        }

        // Delete all letter to this email address
        emailToUserWithCodeRepository.deleteAllByAddress(emailAddress);

        if (new Date().getTime() >= emailLetter.getExpiredAt().getTime()) {
            throw new IllegalArgAppException("That code " + code + " expired");
        }

        if (userRepository.isExistUserByAddress(emailAddress)) {
            User user = userRepository.getUserByAddress(emailAddress);
            return authService.auth(user);
        }

        return authService.auth(create(emailAddress));
    }

    @Override
    public User getUserByUserDetails(final UserDetails userDetails) {
        return userRepository.getUserByAddress(userDetails.getUsername());
    }

    @Override
    public User update(final UserDetails userDetails, final UserUpdateForm userUpdateForm) {
        User user = getUserByUserDetails(userDetails);

        if (userUpdateForm.getFirstName() != null){
            user.setFirstName(userUpdateForm.getFirstName());
        }

        if (userUpdateForm.getLastName() != null){
            user.setLastName(userUpdateForm.getLastName());
        }

        if (userUpdateForm.getUsername() != null){
            if (userRepository.isExistUserByUsername(userUpdateForm.getUsername())){
                throw new IllegalArgAppException("User with such username '" + userUpdateForm.getUsername() + "' exist");
            }

            user.setUsername(userUpdateForm.getUsername());
        }

        return userRepository.save(user);
    }

    @Override
    public User create(final String address) {
        // Create role for user
        Role role = roleRepository.create(RoleType.User);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // Create user
        User user = userRepository.save(
                User.builder()
                        .email(address)
                        .roles(roles)
                        .build()
        );

        // Update Role that entity has a User
        role.setUser(user);
        roleRepository.save(role);

        return user;
    }

    @Override
    public Boolean isExistUserByUsername(final String username) {
        return userRepository.isExistUserByUsername(username);
    }
}