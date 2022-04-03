package org.dapamoha.api.service.interf;

import org.dapamoha.shared.domain.service.AuthenticationUser;
import org.dapamoha.shared.posgresql.entity.User;

public interface AuthService {
    AuthenticationUser auth(User user);
}
