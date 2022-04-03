package org.dapamoha.shared.mongo.repository.letterToUserWithCodeToEmail;

import org.dapamoha.shared.mongo.entity.EmailToUserWithCode;
import org.springframework.stereotype.Repository;

@Repository
public class EmailToUserWithCodeRepositoryImpl implements EmailToUserWithCodeRepository {

    private final EmailToUserWithCodeMongoRepository repository;

    public EmailToUserWithCodeRepositoryImpl(final EmailToUserWithCodeMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmailToUserWithCode save(final EmailToUserWithCode letterToUserWithCodeToEmail) {
        return repository.save(letterToUserWithCodeToEmail);
    }

    @Override
    public EmailToUserWithCode getByAddress(final String address) {
        return repository.getEmailToUserWithCodeByAddress(address);
    }

    @Override
    public void delete(final EmailToUserWithCode emailToUserWithCode) {
        repository.delete(emailToUserWithCode);
    }

    @Override
    public void deleteAllByAddress(final String address) {
        repository.deleteAllByAddress(address);
    }
}
