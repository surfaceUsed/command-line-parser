package org.example.parser;


import org.example.annotations.Parameter;
import org.example.error.DuplicateParameterKeyException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * A utility class that scans an objects fields for the @Parameter annotation and maps each annotated field to its
 * corresponding parameter key.
 *
 * This class is used to establish a link between command-line argument keys and object fields, enabling dynamic
 * assignment of values.
 *
 * It ensures that each parameter key is unique, throwing a DuplicateParameterKeyException if duplicate keys are
 * detected.
 *
 * The resulting field map allows for efficient lookup and assignment, ensuring that a specific parameter key returns
 * its associated field, which can then be updated and set with the parsed input value.
 */
class ObjectFieldMapper {

    private final Map<String, Field> fieldMap = new HashMap<>();

    ObjectFieldMapper(Object object) {
        initFields(object);
    }

    /**
     *
     * Scans the object's fields and maps them to their corresponding parameter keys.
     *
     * @param object The objects whose fields are being processed.
     * @throws DuplicateParameterKeyException If duplicate parameter keys are detected.
     */
    private void initFields(Object object) {
        Class<?> objectClass = object.getClass();

        Field[] objectFields = objectClass.getDeclaredFields();

        for (Field field : objectFields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Parameter.class)) {
                Parameter param = field.getAnnotation(Parameter.class);
                String parameterKey = param.key();
                Field currentField = this.fieldMap.getOrDefault(parameterKey, null);
                if (currentField != null) {
                    throw new DuplicateParameterKeyException("Duplicated key-values in parameter annotation.");
                }
                this.fieldMap.put(parameterKey, field);
            }
        }
    }

    Map<String, Field> getFieldMap() {
        return this.fieldMap;
    }
}
