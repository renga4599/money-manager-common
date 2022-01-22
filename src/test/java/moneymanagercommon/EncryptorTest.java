package moneymanagercommon;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EncryptorTest {

    @Test
    public void testEncryptors() {
        log.info("B Crypt {} ", new BCryptPasswordEncoder().encode("password"));
        log.info("S Crypt {} ", new SCryptPasswordEncoder().encode("password"));
        log.info("Pbkdf2 Crypt {} ", new Pbkdf2PasswordEncoder().encode("password"));

        Map<String, PasswordEncoder> encryptors = new HashMap<>();
        encryptors.put("bCrypt", new BCryptPasswordEncoder());
        encryptors.put("sCrypt", new SCryptPasswordEncoder());
        encryptors.put("pbkdf2 Crypt", new Pbkdf2PasswordEncoder());

        String encodedPasswords = new DelegatingPasswordEncoder("bCrypt", encryptors).encode("password");
        log.info("DelegatingPasswordEncoder {} ", encodedPasswords);
    }
}
