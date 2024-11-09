package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

public class StringSchema {
    private boolean required;
    private int minLength = 0;
    private String containsSubstring;
    private final List<Predicate<String>> checks = new ArrayList<>();

    public StringSchema required() {
        this.required = true;
        checks.add(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        checks.add(value -> value == null || value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        checks.add(value -> value == null || value.contains(containsSubstring));
        return this;
    }

    public boolean isValid(String value) {
        for (Predicate<String> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
