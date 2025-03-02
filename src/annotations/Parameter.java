package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Custom annotation used to map command-line parameters to object fields.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

    /**
     *
     * Defines the key used to match a command-line argument with a field.
     *
     * @return The parameter key.
     */
    String key();

    String description() default "";
}
