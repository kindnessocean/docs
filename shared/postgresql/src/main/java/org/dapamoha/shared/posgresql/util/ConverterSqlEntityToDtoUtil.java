package org.dapamoha.shared.posgresql.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dapamoha.shared.domain.dto.user.PrivateUserDto;
import org.dapamoha.shared.posgresql.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverterSqlEntityToDtoUtil {

    public static PrivateUserDto convert(User user){
        return new PrivateUserDto(
                user.getEmail(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}