package org.dapamoha.shared.mongo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailToUserWithCode {
    @Id
    private String id;
    private String uuid;
    private String address;
    private Date created;
    private Date expiredAt;
    private Integer code;
}
