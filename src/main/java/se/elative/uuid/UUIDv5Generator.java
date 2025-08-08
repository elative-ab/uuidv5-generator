package se.elative.uuid;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/** Generator class fo UUID version 5. This UUID is generated from a SHA-1 hash based on a namespace and a name.
 *
 * The class supports both the standard namespaces (DNS, URL, OID, X500) and custom namespaces.
 *
 * RFC 9562, chapter 6.6 recommends using UUIDv4 or UUIDv7 for 'vendor-specific, application-specific, and
 * deployment-specific Namespace ID values'. Keep in mind that global 'interoperability is not guaranteed', unless
 * the namespace ID is registered with IANA.
 *
 */
public class UUIDv5Generator {
    enum StandardNamespace {
        DNS,
        URL,
        OID,
        X500,
    }

    private final byte[] namespaceBytes;

    /** Constructor to create a generator for a custom namespace.
     *
     * The namspace UUID must be the right format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
     *
     * @param namespaceUuid - UUID for the namespace in String format
     */
    public UUIDv5Generator(String namespaceUuid) {
        this(UUID.fromString(namespaceUuid));
    }

    /** Constructs a generator for the specified namespace UUID.
     *
     * @param namespaceUuid - UUID for the namespace
     */
    public UUIDv5Generator(UUID namespaceUuid) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(namespaceUuid.getMostSignificantBits());
        bb.putLong(namespaceUuid.getLeastSignificantBits());
        namespaceBytes = bb.array();
    }



    /** Factory method to get generators for the standard namespaces.
     *
     * @param standardNamespace - the standard namespace
     * @return - a generator producing UUIDs in the standard namespace
     */
    public static UUIDv5Generator getForStandardNamespace(StandardNamespace standardNamespace) {
        switch (standardNamespace) {
            case DNS:
                return new UUIDv5Generator("6ba7b810-9dad-11d1-80b4-00c04fd430c8");
            case URL:
                return new UUIDv5Generator("6ba7b811-9dad-11d1-80b4-00c04fd430c8");
            case OID:
                return new UUIDv5Generator("6ba7b812-9dad-11d1-80b4-00c04fd430c8");
            case X500:
                return new UUIDv5Generator("6ba7b814-9dad-11d1-80b4-00c04fd430c8");
        }
        throw new IllegalArgumentException("Unknown standard namespace: " + standardNamespace);
    }

    /** Generates a UUID in the generator's namespace
     *
     * @param name - the string to generate a UUID for
     * @return - a UUID version 5 in the generator's namespace
     */
    public UUID fromName(String name) {
        byte[] uuidNameBytes = name.getBytes(StandardCharsets.UTF_8);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can not get SHA-1 digest instance", e);
        }

        digest.update(namespaceBytes);
        digest.update(uuidNameBytes);
        byte[] digestBytes = digest.digest();

        // Set version and variant, RFC 9562 chapter 5.5
        digestBytes[6] &= 0x0f;
        digestBytes[6] |= 0x50;

        digestBytes[8] &= 0x3f;
        digestBytes[8] |= (byte)0x80;

        ByteBuffer bb = ByteBuffer.wrap(digestBytes);
        long msb = bb.getLong();
        long lsb = bb.getLong();
        // Rest of the SHA-1 digest is ignored

        return new UUID(msb, lsb);
    }

}
