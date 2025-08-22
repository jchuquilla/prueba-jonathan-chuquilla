package com.prueba.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Utils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static LocalDate parseDate(String date){

        try {
            return LocalDate.parse(date, FORMATTER);
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("La fecha no tiene formato yyyy-MM-dd", e);
        }
    }

    public static boolean isValidDate(String date){
        try {
            LocalDate.parse(date, FORMATTER);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public static boolean isValidEmail(String email){
        if(email == null || email.isBlank()){
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidIdentificationNumber(String identificationNumber){
        if(identificationNumber == null || !identificationNumber.matches("\\d{10}")){
            return false;
        }

        int provincia = Integer.parseInt(identificationNumber.substring(0, 2));
        if ((provincia < 1 || provincia > 24) && provincia != 30) {
            return false;
        }

        int tercerDigito = Character.getNumericValue(identificationNumber.charAt(2));
        if (tercerDigito < 0 || tercerDigito > 6) {
            return false;
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(identificationNumber.charAt(i));
            if (i % 2 == 0) { // posiciones impares (0,2,4,6,8)
                digito = digito * 2;
                if (digito > 9) digito -= 9;
            }
            suma += digito;
        }

        int decenaSuperior = ((suma + 9) / 10) * 10;
        int digitoVerificador = decenaSuperior - suma;
        if (digitoVerificador == 10) digitoVerificador = 0;

        int ultimoDigito = Character.getNumericValue(identificationNumber.charAt(9));
        return digitoVerificador == ultimoDigito;

    }

}
