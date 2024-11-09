package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorStringSchemaTest {

    @Test
    public void testStringSchemaIsValid() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public void testStringSchemaContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testStringSchemaMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.minLength(4).isValid("what does the fox say"));
        assertFalse(schema.minLength(4).isValid("aa"));
        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
