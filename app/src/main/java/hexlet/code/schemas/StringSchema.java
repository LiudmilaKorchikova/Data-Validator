package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addCheck("required", value -> value != null && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value == null || ((String) value).length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value == null || ((String) value).contains(substring));
        return this;
    }
}
