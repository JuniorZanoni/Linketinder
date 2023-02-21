package utils.service

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ConvertStringInLocalDate {
    static LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}
