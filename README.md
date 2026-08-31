# EncriptIt

EncriptIt is a small Java Swing desktop application for encrypting and decrypting text using two common cryptography approaches:

- AES-256
- RSA

The project is a straightforward educational/demo tool that lets you choose an algorithm, provide the required keys or password material, enter text, and either encrypt or decrypt the content.

## Features

- Select between AES-256 and RSA from the GUI
- Set salt and secret key values for AES
- Load RSA public and private keys from files or generated values
- Enter text manually or read it from a file
- Encrypt or decrypt the current text
- View the result in the app and save it to a file
- Works as a lightweight local encryption utility for learning and experimentation

## Project structure

- [src/MainFrame.java](src/MainFrame.java) - Swing interface and workflow controller
- [src/Algorithm/AES256.java](src/Algorithm/AES256.java) - AES encryption/decryption logic
- [src/Algorithm/RSA.java](src/Algorithm/RSA.java) - RSA key handling and encryption/decryption logic
- [bin/](bin/) - compiled bytecode output directory

## How it works

### AES-256 flow

The AES implementation uses:

- PBKDF2WithHmacSHA256 for key derivation
- a user-provided salt
- a user-provided secret key
- a random IV generated for each encryption
- CBC mode with PKCS5 padding

This makes the app suitable for simple text encryption experiments, but it is not a production-grade hardened crypto design.

### RSA flow

The RSA implementation:

- generates or accepts a 2048-bit key pair
- stores public and private keys as Base64-encoded values
- encrypts and decrypts text using standard Java crypto APIs

RSA is best for small payloads and key exchange, not for large files or full-document encryption in a real-world system.

## Requirements

- Java JDK 8 or newer
- A desktop environment capable of running Swing applications

## Run the application

From the project root, compile the Java sources and launch the app:

```bash
javac -d bin src/MainFrame.java src/Algorithm/AES256.java src/Algorithm/RSA.java
java -cp bin MainFrame
```

On Windows, the same commands work in Command Prompt or PowerShell.

## Typical usage

1. Start the application.
2. Choose AES-256 or RSA.
3. Enter the required values:
   - AES: salt + secret key
   - RSA: public key + private key
4. Select Encrypt or Decrypt.
5. Type or load the text.
6. Review the result and save it if needed.

## Security notes

This project is intended for learning and local experimentation. It is not a substitute for a full secure application workflow.

Some practical considerations:

- AES in CBC mode should ideally be replaced with an authenticated mode such as GCM in a production system.
- RSA should be used with secure padding and proper key management in real deployments.
- Keep private keys protected and avoid hardcoding secrets in source code.

## License

No explicit license file is present in the repository at this time, so the project is currently provided as-is without a formal license declaration.
