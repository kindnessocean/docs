package org.dapamoha.shared.posgresql.repository.user;


import org.dapamoha.shared.posgresql.entity.User;

public interface UserRepository {

    Boolean isExistUserByAddress(String emailAddress);

    User getUserByAddress(String emailAddress);

    User getUserByEmail(String email);

    User save(User user);

    User getUserByUsername(String username);

    Boolean isExistUserByUsername(String username);
}
