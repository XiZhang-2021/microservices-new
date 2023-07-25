package org.programming.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class KeyGenerator {
    public Key generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a 256-bit secret key
    }
}

