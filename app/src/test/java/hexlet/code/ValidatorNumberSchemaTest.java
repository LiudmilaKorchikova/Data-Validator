package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ValidatorNumberSchemaTest {

    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    public void setUp() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public void testNumberSchemaRequired() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    public void testNumberSchemaPositive() {
        schema.positive();

        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testNumberSchemaRange() {
        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(8));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testNumberSchemaMultipleValidations() {
        schema.range(8, 9).required().positive().range(1, 10);

        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(-5));
    }
}
