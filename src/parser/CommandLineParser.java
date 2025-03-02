package parser;

import java.lang.reflect.Field;
import java.util.Map;

/**
 *
 * A utility class responsible for parsing command-line arguments and mapping them to the field of a given object.
 *
 * This class extracts key-value pairs from the command-line arguments, and assigns values to the corresponding fields
 * in the provided object, based on parameter annotations.
 *
 * How it works:
 *
 *    1. It first maps the command-line arguments using 'InputArgumentMapper'.
 *    2. Then it retrieves the objects annotated fields using 'ObjectFieldMapper'.
 *    3. Lastly it assigns values to the objects fields based on the matching parameter keys.
 *
 * If a matching key is found in the command-line arguments, the corresponding field is updated dynamically using
 * Java Reflection.
 */
public class CommandLineParser {

    private CommandLineParser() {}

    /**
     *
     * Parses the given command-line arguments and maps them to the fields of the provided object.
     *
     * This method takes an object with annotated fields, and an array of command-line arguments. It processes each
     * parameter using two helper classes:
     *
     *    1. 'InputArgumentMapper' -> Parses the command-line arguments and stores them as key-value pairs.
     *    2. 'ObjectFieldMapper' -> Scans the objects fields for the @Parameter annotation and maps them to their
     *        corresponding parameter key.
     *
     * After processing, it iterates through the mapped fields of the object, checking if a matching key exists in the
     * parsed input. If a match is found, the corresponding value is assigned to the objects field using Java
     * Reflection.
     *
     * @param object The object whose fields should be populated with command-line arguments,
     * @param args The command-line arguments in key-value pairs.
     * @throws IllegalAccessException If access to a field is denied when attempting to set its value.
     */
    public static void runParser(Object object, String[] args) throws IllegalAccessException {

        final InputArgumentMapper inputMapper = new InputArgumentMapper(args);
        final ObjectFieldMapper objectFieldMapper = new ObjectFieldMapper(object);

        for (Map.Entry<String, Field> keyValues : objectFieldMapper.getFieldMap().entrySet()) {
            String key = keyValues.getKey();
            String inputValue = inputMapper.getInputMap().getOrDefault(key, "");
            if (!inputValue.isEmpty()) {
                Field updateField = keyValues.getValue();
                updateField.set(object, inputValue);
            }
        }
    }
}
