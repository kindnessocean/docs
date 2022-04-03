package org.dapamoha.api.service.interf;

import org.dapamoha.shared.domain.dto.user.UserUpdateForm;
import org.dapamoha.shared.domain.service.AuthenticationUser;
import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;
import org.dapamoha.shared.posgresql.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    EmailToUserWithCode requestEmailCode(String address);

    AuthenticationUser requestJwtToken(String emailAddress, Integer code);

    User getUserByUserDetails(UserDetails userDetails);

    User update(UserDetails userDetails, UserUpdateForm userUpdateForm);

    User create(String address);

    Boolean isExistUserByUsername(String username);
}
