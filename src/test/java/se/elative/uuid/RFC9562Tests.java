package se.elative.uuid;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RFC9562Tests {

    @Test
    public void chapter_A_4() {
        String uuidName = "www.example.com";
        UUIDv5Generator generator = new UUIDv5Generator("6ba7b810-9dad-11d1-80b4-00c04fd430c8");
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("2ed6657d-e927-568b-95e1-2665a8aea6a2", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

}
