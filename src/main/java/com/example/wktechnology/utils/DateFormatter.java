package com.example.wktechnology.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDate parseDataNascimento(String dataNascString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataNascString, formatter);
    }
}
