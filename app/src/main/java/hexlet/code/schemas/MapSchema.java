package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<T, T>> {

    public MapSchema required() {
        addCheck(value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<T>> newMapSchema) {
        addCheck(value -> validate(value, newMapSchema));
        return this;
    }

    private boolean validate(Map<?, ?> map, Map<String, BaseSchema<T>> mapSchema) {
        if (map == null) {
            return true;
        }

        for (Map.Entry<String, BaseSchema<T>> entry : mapSchema.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();
            Object value = map.get(key);

            // Преобразуем значение value в тип T, чтобы корректно передать в isValid
            if (!isValueValidForSchema(schema, value)) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean isValueValidForSchema(BaseSchema<T> schema, Object value) {
        try {
            T castValue = (T) value;
            return schema.isValid(castValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
