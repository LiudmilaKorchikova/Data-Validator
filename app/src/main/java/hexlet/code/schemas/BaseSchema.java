package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new HashMap<>();

    /**
     * Adds a check to this schema. Subclasses should call this method to
     * add custom validation logic. The last added check will have priority.
     * @param key the unique key for the check
     * @param check the validation check to be added\
     */
    protected void addCheck(String key, Predicate<T> check) {
        checks.put(key, check);
    }

    /**
     * Marks the value as required. If the value is null, the validation fails.
     * This method is now available for all schemas.
     * @return the schema object itself for method chaining
     */
    public BaseSchema<T> required() {
        addCheck("required", value -> value != null);
        return this;
    }

    /**
     * Validates the given value. Subclasses should implement their own validation logic.
     *
     * @param value the value to be validated
     * @return true if the value is valid, false otherwise
     */
    public boolean isValid(T value) {
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
