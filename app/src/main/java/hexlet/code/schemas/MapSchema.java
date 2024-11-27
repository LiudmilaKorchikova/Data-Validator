package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeOf", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> newMapSchema) {
        addCheck("shape", value -> validate(value, newMapSchema));
        return this;
    }

    private boolean validate(Map<?, ?> map, Map<String, BaseSchema<String>> newMapSchema) {
        if (map == null) {
            return true;
        }

        for (Map.Entry<String, BaseSchema<String>> entry : newMapSchema.entrySet()) {
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
