package com.codecademy.logic;

/**
 * 
 * The Logic class contains several utility methods for validating input data.
 */
public class Logic {

    /**
     * 
     * Checks if the provided day, month and year form a valid date.
     * 
     * @param day   the day of the date
     * @param month the month of the date
     * @param year  the year of the date
     * @return true if the date is valid, false otherwise
     */
    public static boolean dateTool(int day, int month, int year) {
        if (month >= 1 && month <= 12) {
            if (day >= 1 && day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                    || month == 10 || month == 12)) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) {
                return true;
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (day >= 1 && day <= 29) {
                        return true;
                    }
                } else {
                    if (day >= 1 && day <= 28) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * Checks if the provided email address is valid.
     * 
     * @param mailAddress the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean mailTool(String mailAddress) {
        // Explain of the regex key
        // ^ The beginning of a line
        // [] A group of
        // \\ disables in line code like \n
        // w-_.+ any letter or number and dash, underscore, dot and plus only once
        // * zero or more after this
        // () as a capturing group
        // $ The end of a line
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]*[\\w]$";
        // return outcome determined whether email matches the regex key
        return mailAddress.matches(regex);
    }

    /**
     * 
     * Formats the provided postal code to match the expected format.
     * 
     * @param postalCode the postal code to format
     * @return the formatted postal code
     * @throws NullPointerException     if the provided postal code is null
     * @throws IllegalArgumentException if the provided postal code is not in the
     *                                  expected format
     */
    public static String postalCode(String postalCode) {
        if (postalCode == null) {
            throw new NullPointerException();
        } else if (Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
                Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
                postalCode.trim().substring(4).trim().length() == 2 &&
                'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
                'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) &&
                postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z') {
            String result = postalCode.trim().substring(0, 4) + " " +
                    postalCode.trim().substring(4).trim().toUpperCase();
            return result;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Checks if a given grade is valid.
     *
     * @param grade the grade to check
     * @return true if the grade is valid, false otherwise
     */
    public boolean isValidGrade(Double grade) {
        return grade >= 1 && grade <= 10;
    }
}
