package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();

    /**
     * Adds a check to this schema. Subclasses should call this method to
     * add custom validation logic.
     *
     * @param check the validation check to be added
     */
    protected void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    /**
     * Validates the given value. Subclasses should implement their own validation logic.
     *
     * @param value the value to be validated
     * @return true if the value is valid, false otherwise
     */
    public boolean isValid(T value) {
        for (Predicate<T> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
