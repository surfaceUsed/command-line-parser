package org.example.error;

/**
 *
 * Exception thrown when duplicate parameter keys are detected in command-line input or object fields.
 */
public class DuplicateParameterKeyException extends RuntimeException {

    public DuplicateParameterKeyException(String message) {
        super(message);
    }
}
