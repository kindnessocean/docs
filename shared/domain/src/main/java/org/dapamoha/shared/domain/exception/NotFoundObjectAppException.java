package org.dapamoha.shared.domain.exception;

public class NotFoundObjectAppException extends AppException {
    public NotFoundObjectAppException(final String message) {
        super(message);
    }

    public NotFoundObjectAppException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
