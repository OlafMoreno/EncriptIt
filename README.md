# EncriptIt

## Algorithms

### AES-256

AES-256 is a symmetric block cipher that uses a 256-bit key. It operates on 128-bit blocks and is typically combined with a mode of operation (for example, CBC or GCM) and a unique initialization vector (IV) per message. AES provides fast, secure confidentiality for large amounts of data; when used in authenticated modes like GCM it also provides integrity.

- Key: 256 bits (32 bytes).
- Typical use: encrypting data at rest or in transit (bulk data).
- Recommendation: use an authenticated mode (GCM) or add a MAC; use random IVs and avoid reusing the same key+IV.

The related implementation in this repository is in `src/Algorithm/AES256.java`.

### RSA

RSA is an asymmetric algorithm (public/private key) based on the factorization of large integers. It is used to encrypt small pieces of data (such as keys) and for digital signatures.

- Keys: typically 2048 bits or larger (3072+ recommended for longer-term security).
- Use: key encryption (do not encrypt large files directly) and signatures.
- Secure padding: use OAEP for encryption and PSS for signatures in modern implementations.

Typical hybrid flow: generate an AES-256 key to encrypt the content (fast), and encrypt that symmetric key with the recipient's RSA public key (hybrid encryption). The recipient decrypts the AES key with their RSA private key and then decrypts the data.

The related implementation in this repository is in `src/Algorithm/RSA.java`.
