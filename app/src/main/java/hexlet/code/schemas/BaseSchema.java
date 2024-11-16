package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    // Используем Map для хранения валидаторов по типам
    private final Map<Class<?>, Predicate<T>> checks = new HashMap<>();

    /**
     * Adds a check to this schema. Subclasses should call this method to
     * add custom validation logic. The last added check will have priority.
     *
     * @param check the validation check to be added
     */
    protected void addCheck(Predicate<T> check) {
        // Добавляем проверку с типом класса валидатора в качестве ключа
        checks.put(check.getClass(), check);
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
