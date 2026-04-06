package io.github.rveggab.auth.infrastructure.utils.helpers;

import io.github.rveggab.auth.domain.exceptions.InvalidDataException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClaveGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "@#$%^&+=.!¡-_?¿:()";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;
    private static final SecureRandom random = new SecureRandom();

    public static String buildClave(String base, int length){
        StringBuilder homoclave = new StringBuilder(length);

        for (int i = 0; i<length; i++){
            homoclave.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return base.toUpperCase().concat("-".concat(homoclave.toString()));
    }


    public static String generatePassword(int length){
        if (length < 12)
            throw new InvalidDataException("The password need a minimum of 12 characters");

        List<Character> password = new ArrayList<>();

        password.add(UPPER.charAt(random.nextInt(UPPER.length())));
        password.add(LOWER.charAt(random.nextInt(LOWER.length())));
        password.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.add(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            password.add(ALL.charAt(random.nextInt(ALL.length())));
        }

        Collections.shuffle(password, random);

        StringBuilder sb = new StringBuilder();
        for (char c : password) sb.append(c);

        return sb.toString();
    }
}
