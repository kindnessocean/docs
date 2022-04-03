package org.dapamoha.shared.posgresql.repository.role;

import org.dapamoha.shared.posgresql.entity.Role;
import org.dapamoha.shared.posgresql.entity.RoleType;

public interface RoleRepository {
    Role create(RoleType roleType);

    Role save(Role role);
}
