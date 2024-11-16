package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck(value -> value != null && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(value -> value != null && ((String) value).length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(value -> value != null && ((String) value).contains(substring));
        return this;
    }
}
