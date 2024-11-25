package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0); // null проверяется сразу
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value == null || value >= min && value <= max);
        return this;
    }
}
