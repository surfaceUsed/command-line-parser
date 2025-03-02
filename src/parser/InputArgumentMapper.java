package parser;

import error.DuplicateParameterKeyException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * A utility class that processes command-line arguments and maps parameter keys to their corresponding values. It
 * converts an array of arguments into a structured map, where each key is associated with its respective value:
 *
 * {"-k", "one", "-v", "two"} -> maps '-k' to 'one', and '-v' to 'two'.
 *
 * If a duplicate key is encountered in the input a 'DuplicateParameterKeyException' is thrown to prevent conflicting
 * values.
 */
class InputArgumentMapper {

    private final Map<String, String> inputMap = new HashMap<>();

    InputArgumentMapper(String[] args) {
        initInput(args);
    }

    /**
     *
     * Parses the command-line arguments and stores them as key-value pairs in a map.
     *
     * @param args The command-line arguments in key-value format.
     * @throws DuplicateParameterKeyException If duplicate keys are found in the input.
     */
    private void initInput(String[] args) {
        int index = 0;
        while (index < args.length) {
            String currentValue = this.inputMap.getOrDefault(args[index], "");
            if (!currentValue.isEmpty()) {
                throw new DuplicateParameterKeyException("Duplicated key-values in command line input.");
            }
            this.inputMap.put(args[index], args[++index]);
            index++;
        }
    }

    Map<String, String> getInputMap() {
        return this.inputMap;
    }
}
