package org.dapamoha.shared.posgresql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    User("ROLE_USER"),
    Moderator("ROLE_MODERATOR"),
    Admin("ROLE_ADMIN");

    private final String value;

}
