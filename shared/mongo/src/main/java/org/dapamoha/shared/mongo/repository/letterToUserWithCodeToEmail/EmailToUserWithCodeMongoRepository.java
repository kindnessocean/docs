package org.dapamoha.shared.mongo.repository.letterToUserWithCodeToEmail;

import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmailToUserWithCodeMongoRepository extends MongoRepository<EmailToUserWithCode, String> {
    EmailToUserWithCode getEmailToUserWithCodeByAddress(String address);

    void deleteAllByAddress(String address);
}
