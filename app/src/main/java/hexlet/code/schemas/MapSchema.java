package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck(value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value == null || value.size() == size );
        return this;
    }
}
