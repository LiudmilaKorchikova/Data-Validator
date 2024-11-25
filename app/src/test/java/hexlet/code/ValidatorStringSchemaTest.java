package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ValidatorStringSchemaTest {

    private Validator v;
    private StringSchema schema;

    @BeforeEach
    public void setUp() throws Exception {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void testStringSchemaRequired() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public void testStringSchemaContains() {
        schema.contains("what");

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("blablawhatbla"));
        assertFalse(schema.isValid("blablabla"));
    }

    @Test
    public void testStringSchemaMinLength() {
        schema.minLength(4);

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("aa"));
    }

    @Test
    public void testStringSchemaMultipleValidations() {
        schema.minLength(10).contains("fox").minLength(4);

        assertTrue(schema.isValid("what does the fox say"));

        schema.minLength(4).contains("whatthe");

        assertFalse(schema.isValid("what does the fox say"));
    }
}
