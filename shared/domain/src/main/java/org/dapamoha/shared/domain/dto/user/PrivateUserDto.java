package org.dapamoha.shared.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateUserDto {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
}
