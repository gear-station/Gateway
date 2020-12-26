package com.gearstation.gateway.resource.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Description: PBKDF2Encoder <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-08-06 06:06 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@Component
public class PBKDF2Encoder implements PasswordEncoder {
    private String secret="mysecret";

    private Integer iteration=23;

    private Integer keylength=256;

    /**
     * More info (https://www.owasp.org/index.php/Hashing_Java)
     * @param cs password
     * @return encoded password
     */
    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }

}
