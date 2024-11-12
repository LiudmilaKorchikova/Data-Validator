package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    private Predicate<Object> lastCheck;

    public StringSchema required() {
        lastCheck = value -> value instanceof String && !((String) value).isEmpty();
        return this;
    }

    public StringSchema minLength(int length) {
        lastCheck = value -> value instanceof String && ((String) value).length() >= length;
        return this;
    }

    public StringSchema contains(String substring) {
        lastCheck = value -> value instanceof String && ((String) value).contains(substring);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        return lastCheck == null || lastCheck.test(value);
    }
}

