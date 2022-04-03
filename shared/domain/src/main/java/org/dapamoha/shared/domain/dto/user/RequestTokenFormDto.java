package org.dapamoha.shared.domain.dto.user;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTokenFormDto implements Serializable {
    private String email;
    private String code;
}
