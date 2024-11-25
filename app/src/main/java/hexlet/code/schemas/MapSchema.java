package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<String>> mapSchema;

    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeOf", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> newMapSchema) {
        this.mapSchema = newMapSchema;
        addCheck("shape", this::validate);
        return this;
    }

    private <T> boolean validate(Map<?, ?> map) {
        if (map == null) {
            return true;
        }

        for (Map.Entry<String, BaseSchema<String>> entry : this.mapSchema.entrySet()) {
            String key = entry.getKey();
            BaseSchema<String> schema = entry.getValue();
            Object value = map.get(key);
            String castValue = (String) value;

            if (!schema.isValid(castValue)) {
                return false;
            }
        }
        return true;
    }
}
