package org.example.enums;

/**
 *
 * Java primitives.
 */
public enum DataTypes {

    STRING("String", "String"),
    INTEGER("int", "Integer"),
    DOUBLE("double", "Double"),
    LONG("long", "Long"),
    BYTE("byte", "Byte"),
    CHARACTER("char", "Character"),
    SHORT("short", "Short"),
    FLOAT("float", "Float"),
    BOOLEAN("boolean", "Boolean");

    private final String primitiveKey;
    private final String objectKey;

    DataTypes(String primitiveKey, String objectKey) {
        this.primitiveKey = primitiveKey;
        this.objectKey = objectKey;
    }

    public static DataTypes getDataType(String input) {
        for (DataTypes type : DataTypes.values()) {
            if (type.primitiveKey.equals(input) || type.objectKey.equals(input)) {
                return type;
            }
        }
        return null;
    }
}
