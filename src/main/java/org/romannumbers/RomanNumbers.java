package org.romannumbers;

import java.util.*;
import java.util.regex.*;

public class RomanNumbers {
    public static boolean isValid(String romanNumber)
            throws IllegalArgumentException {
        if (romanNumber.length() < 1 || romanNumber.length() > 15)
            throw new IllegalArgumentException("Expected roman number length to be between 1 and 15 chars");

        // Rules:
        // 1. Only use of I, V, X, L, C, D, M allowed
        // All letters should be either uppercase or
        // lowercase, but not both in a single number.
        // 2. A digit may not be repeated more than 3 times
        // (except for the digit M and the number IIII).
        // Digits V, L, D cannot be repeated.
        // 3. If a digit is placed before a greater one,
        // it should not be repeated, has to be a power of
        // 10 (I, X, or C) and cannot be more than 10 times
        // smaller than the following one. Also, there shouldn't
        // be 3 digits in a row so that digit 1 <= digit 2 < digit 3.

        // If some letters are uppercase and some are lowercase...
        if (romanNumber.compareTo(romanNumber.toLowerCase()) != 0 &&
            romanNumber.compareTo(romanNumber.toUpperCase()) != 0)
            return false;

        Pattern romanNumberPattern = Pattern.compile(
                "^(M*(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|IIII)$",
                Pattern.CASE_INSENSITIVE
        );

        return romanNumberPattern.matcher(romanNumber).matches();
    }

    public static int convert(String romanNumber)
            throws IllegalArgumentException {
        if (!isValid(romanNumber))
            throw new IllegalArgumentException("Roman number " + romanNumber + " is not valid");

        int arabicNumber = 0;
        Map<Character, Integer> digitValues = new HashMap<>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        String romanNumberCaps = romanNumber.toUpperCase();
        for (int i = 0; i < romanNumberCaps.length(); i++) {
            int digitVal = digitValues.get(romanNumberCaps.charAt(i));
            if (i < romanNumberCaps.length() - 1 &&
                    digitVal < digitValues.get(romanNumberCaps.charAt(i + 1)))
                arabicNumber -= digitVal;
            else arabicNumber += digitVal;
        }

        return arabicNumber;
    }
}
