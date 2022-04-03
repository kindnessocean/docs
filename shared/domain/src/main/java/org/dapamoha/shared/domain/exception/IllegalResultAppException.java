package org.dapamoha.shared.domain.exception;

public class IllegalResultAppException extends AppException {
    public IllegalResultAppException(final String message) {
        super(message);
    }

    public IllegalResultAppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
