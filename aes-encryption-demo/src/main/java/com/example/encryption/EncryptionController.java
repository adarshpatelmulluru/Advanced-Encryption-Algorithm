package com.example.encryption;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String plainText) {
        return EncryptionService.encrypt(plainText);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedText) {
        return EncryptionService.decrypt(encryptedText);
    }
}
