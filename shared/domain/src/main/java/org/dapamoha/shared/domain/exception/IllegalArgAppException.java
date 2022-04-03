package org.dapamoha.shared.domain.exception;

public class IllegalArgAppException extends AppException {
    public IllegalArgAppException(final String message) {
        super(message);
    }

    public IllegalArgAppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
