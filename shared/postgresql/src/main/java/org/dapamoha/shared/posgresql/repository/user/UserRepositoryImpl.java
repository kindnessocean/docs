package org.dapamoha.shared.posgresql.repository.user;

import org.dapamoha.shared.posgresql.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(final UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Boolean isExistUserByAddress(final String emailAddress) {
        return userJpaRepository.existsUserByEmail(emailAddress);
    }

    @Override
    public User getUserByAddress(final String emailAddress) {
        return userJpaRepository.getUserByEmail(emailAddress);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userJpaRepository.getUserByEmail(email);
    }


    @Override
    public User save(final User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User getUserByUsername(final String username) {
        return userJpaRepository.getUserByUsername(username);
    }

    @Override
    public Boolean isExistUserByUsername(final String username) {
        return getUserByUsername(username) != null;
    }
}
