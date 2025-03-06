package org.example.parser;

import org.example.enums.DataTypes;
import org.example.error.ArgumentDataTypeException;
import org.example.error.ObjectFieldDataTypeException;
import java.lang.reflect.Field;

/**
 *
 * Utility class for validating and converting command-line argument values to their corresponding primitive data types.
 */
class DataTypeValidator {

    /**
     * Parses and converts a string value to its corresponding primitive data type based on the specified field's type.
     *
     * This method determines the expected data type of the given field, validates whether it is a supported primitive
     * type, and attempts to convert the provided string data into that type. If the field's type is not a recognized
     * primitive type, an exception is thrown. If the provided data string cannot be correctly converted to a primitive
     * type, an exception gets thrown.
     *
     * @param data  The string representation of the value to be converted.
     * @param field The field whose data type will be used for conversion.
     * @return The converted value as an Object, matching the expected data type of the field.
     * @throws ObjectFieldDataTypeException If the field's data type is not a recognized primitive type.
     * @throws ArgumentDataTypeException If the provided data cannot be converted to the expected primitive type.
     */
    static Object parseFieldDataType(String data, Field field) {
        String fieldName = field.getName();
        String fieldDataType = field.getType().getSimpleName();
        DataTypes type = DataTypes.getDataType(fieldDataType);
        if (type == null) {
             throw new ObjectFieldDataTypeException("The '" + fieldDataType + "' data type of field '" + fieldName +
                     "' is not a primitive type.");
        }
        try {
            return switch (type) {
                case STRING -> data;
                case INTEGER -> integerType(data);
                case DOUBLE -> doubleType(data);
                case LONG -> longType(data);
                case CHARACTER -> characterType(data);
                case BOOLEAN -> booleanType(data);
                case SHORT -> shortType(data);
                case FLOAT -> floatType(data);
                case BYTE -> byteType(data);
            };
        } catch (ArgumentDataTypeException e) {
            throw new ArgumentDataTypeException("'" + data + "' does not match the data type '" + fieldDataType +
                    "' of the object field '" + fieldName +"'.", e);
        }
    }

    private static Integer integerType(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }

    private static Double doubleType(String data) {
        try {
            return Double.parseDouble(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }

    private static Long longType(String data) {
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }

    private static Character characterType(String data) {
        if (data.length() == 1 && Character.isLetter(data.charAt(0))) {
            return data.charAt(0);
        }
        throw new ArgumentDataTypeException();
    }

    private static Boolean booleanType(String data) {
        if (data.equals("true") || data.equals("false")) {
            return Boolean.parseBoolean(data);
        }
        throw new ArgumentDataTypeException();
    }

    private static Short shortType(String data) {
        try {
            return Short.parseShort(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }

    private static Float floatType(String data) {
        try {
            return Float.parseFloat(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }

    private static Byte byteType(String data) {
        try {
            return Byte.parseByte(data);
        } catch (NumberFormatException e) {
            throw new ArgumentDataTypeException();
        }
    }
}