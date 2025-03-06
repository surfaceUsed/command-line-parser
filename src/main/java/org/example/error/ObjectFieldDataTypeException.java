package org.example.error;

/**
 *
 * Exception thrown when a data type of the object field is not a Java primitive.
 */
public class ObjectFieldDataTypeException extends RuntimeException {

    public ObjectFieldDataTypeException(String message) {
        super(message);
    }
}
