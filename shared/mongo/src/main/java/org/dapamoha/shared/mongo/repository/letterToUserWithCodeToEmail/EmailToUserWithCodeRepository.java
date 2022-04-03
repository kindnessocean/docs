package org.dapamoha.shared.mongo.repository.letterToUserWithCodeToEmail;

import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;

public interface EmailToUserWithCodeRepository {
    EmailToUserWithCode save(EmailToUserWithCode letterToUserWithCodeToEmail);

    EmailToUserWithCode getByAddress(String address);

    void delete(EmailToUserWithCode emailToUserWithCode);

    void deleteAllByAddress(String address);
}
