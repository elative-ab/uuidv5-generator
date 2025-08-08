package se.elative.uuid;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomNamespaceTests {
    private static final String CUSTOM_NAMESPACE_UUID = "e2d304b7-67dd-480b-83e3-68484a11e84f";


    @Test
    public void custom_event_and_attribute() {
        // uuidgen --namespace e2d304b7-67dd-480b-83e3-68484a11e84f --name "event-1/attribute-2" --sha1
        String uuidName = "event-1/attribute-2";
        UUIDv5Generator generator = new UUIDv5Generator(CUSTOM_NAMESPACE_UUID);

        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("94bcbe7b-f3cb-505f-ad9e-1b31c89274f7", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void custom_constructor_with_uuid() {
        // uuidgen --namespace e2d304b7-67dd-480b-83e3-68484a11e84f --name "event-1/attribute-2" --sha1
        String uuidName = "event-1/attribute-2";
        UUID namespaceUuid = UUID.fromString(CUSTOM_NAMESPACE_UUID);
        UUIDv5Generator generator = new UUIDv5Generator(namespaceUuid);

        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("94bcbe7b-f3cb-505f-ad9e-1b31c89274f7", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void custom_namespace_invalid_uuid() {
        assertThrows(IllegalArgumentException.class, ()
                -> new UUIDv5Generator("invalid-uuid"));
    }
}
