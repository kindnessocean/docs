package org.dapamoha.shared.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CodeUtil {

    private static final String SEPARATOR = "-";
    private static final String alphabet = "0123456789";
    private static final Integer CODE_LENGTH = 8;
    private static final int alphabetLength = alphabet.length();

    public static Integer generateCodeAsNumber(){
        StringBuilder builder = new StringBuilder();

        for (long i = 1; i < CODE_LENGTH; i++) {
            builder.append(getRandomNumber());
        }

        return Integer.valueOf(builder.toString());
    }

    private static char getRandomNumber(){
        double rawIndex = Math.random() * alphabetLength;
        int index = (int) rawIndex;
        return  alphabet.charAt(index);
    }
}
