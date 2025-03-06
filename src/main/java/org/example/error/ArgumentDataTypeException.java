package org.example.error;

/**
 *
 * Exception thrown when an argument value from command-line cannot be converted to a Java primitive.
 */
public class ArgumentDataTypeException extends RuntimeException {

    public ArgumentDataTypeException() {

    }

    public ArgumentDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
