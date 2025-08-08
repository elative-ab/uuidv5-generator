package se.elative.uuid;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CliTests {

    @Test
    public void dns_example_com() {
        // uuidgen --namespace @dns --name www.example.com --sha1
        String uuidName = "www.example.com";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.DNS);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("2ed6657d-e927-568b-95e1-2665a8aea6a2", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void dns_google_com() {
        // uuidgen --namespace @dns --name google.com --sha1
        String uuidName = "google.com";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.DNS);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("64ee70a4-8cc1-5d25-abf2-dea6c79a09c8", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void url_example_com() {
        // uuidgen --namespace @url --name www.example.com --sha1
        String uuidName = "www.example.com";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.URL);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("b63cdfa4-3df9-568e-97ae-006c5b8fd652", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void url_google_com() {
        // uuidgen --namespace @url --name google.com --sha1
        String uuidName = "google.com";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.URL);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("fedb2fa3-8f5c-5189-80e6-f563dd1cb8f9", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void url_latin1_characters() {
        // uuidgen --namespace @url --name "www.url-with-å-ö" --sha1
        String uuidName = "www.url-with-å-ö";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.URL);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("3e38f054-e666-5396-b9ad-95345d55d6cc", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }


    @Test
    public void oid_cpu_load_average_5_min() {
        // uuidgen --namespace @oid --name "1.3.6.1.4.1.2021.10.1.3.2" --sha1
        String uuidName = "1.3.6.1.4.1.2021.10.1.3.2";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.OID);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("7c1f01c2-1b62-55f9-afd2-389fd979f06b", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void oid_sys_uptime() {
        // uuidgen --namespace @oid --name "1.3.6.1.2.1.1.3.0" --sha1
        String uuidName = "1.3.6.1.2.1.1.3.0";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.OID);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("12583d78-247f-5fb6-a9ec-461516c56251", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }


    @Test
    public void x500_john_doe() {
        // uuidgen --namespace @x500 --name "CN=John Doe, OU=Engineering, O=ExampleCorp, C=US" --sha1
        String uuidName = "CN=John Doe, OU=Engineering, O=ExampleCorp, C=US";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.X500);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("fd6cf599-f15a-5883-8709-785973dd6d0c", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }

    @Test
    public void x500_latin_1_name() {
        // uuidgen --namespace @x500 --name "CN=Göran Månsson, OU=Engineering, O=ExampleCorp, C=US" --sha1
        String uuidName = "CN=Göran Månsson, OU=Engineering, O=ExampleCorp, C=US";
        UUIDv5Generator generator = UUIDv5Generator.getForStandardNamespace(UUIDv5Generator.StandardNamespace.X500);
        UUID namedUuid = generator.fromName(uuidName);

        String actualUuid = namedUuid.toString();
        assertEquals("7a075626-cd20-5933-b5df-f3670380a82b", actualUuid);
        assertEquals(5, namedUuid.version());
        assertEquals(2, namedUuid.variant());
    }


}
