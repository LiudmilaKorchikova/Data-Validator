package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {
    private boolean required;

    public NumberSchema required() {
        addCheck(value -> value != null);
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck(value -> value == null ? !required : value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(value -> value != null && value >= min && value <= max);
        return this;
    }
}
