package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<String>> mapSchema;

    public MapSchema required() {
        addCheck(value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> newMapSchema) {
        this.mapSchema = newMapSchema;
        addCheck(this::validate);
        return this;
    }

    private boolean validate(Map<?, ?> map) {
        if (map == null) {
            return true;
        }

        for (Map.Entry<String, BaseSchema<String>> entry : this.mapSchema.entrySet()) {
            String key = entry.getKey();
            BaseSchema<String> schema = entry.getValue();
            Object value = map.get(key);

            if (!isValueValidForSchema(schema, value)) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean isValueValidForSchema(BaseSchema<T> schema, Object value) {
        try {
            @SuppressWarnings("unchecked")
            T castValue = (T) value;
            return schema.isValid(castValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
