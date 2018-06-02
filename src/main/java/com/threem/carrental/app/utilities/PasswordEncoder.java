package com.threem.carrental.app.utilities;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

// #RW nie ma potrzeby aby ta klasa była komponentem, metoda encode może być statyczna
// Proponuję podpiąć logger to logowania błędów.

/**
 * @author marek_j on 2018-05-10
 */
@Component
public class PasswordEncoder {
    //todo this class should be used until spring security is not implemented

    public String encode(String password) {
        InputStream stream = new ByteArrayInputStream(password.getBytes(StandardCharsets.UTF_8));
        try {
            return DigestUtils.md5DigestAsHex(stream).toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
