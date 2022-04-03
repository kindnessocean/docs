package org.dapamoha.shared.posgresql.repository.role;

import java.util.Optional;
import org.dapamoha.shared.posgresql.entity.Role;
import org.dapamoha.shared.posgresql.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleType roleType);
}
