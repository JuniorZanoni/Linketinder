package Utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Utils {
    static String regexNome = /[A-Z][a-zéãíóúç]+/
    static String regexSobrenome = /([A-Z][a-zéãíóúç]+\s?)+/
    static String regexEmail = '^(.+)@(\\S+)\\.(.+)$'
    static String regexCpf = '[0-9]{11}'
    static String regexPais = /([A-Z][a-zéãíóúç]+\s?)+/
    static String regexCep = "[0-9]{8}"
    static String regexDescricao = ".+"
    static String regexSenha = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#_!\\-])[0-9a-zA-Z$*&@#_!-]{6,}'
    static String regexDataDeNascimento = '^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}$'
    static String regexNomeEmpresa = /[A-Za-z0-9éãíóúç\-õ\s]+/
    static String regexCnpj = '[0-9]{14}'

    static clearConsole() {
        30.times { println "" }
    }

    static inputString(String regex, String message) {
        while (true) {
            Utils.clearConsole()
            println message
            Scanner sc = new Scanner(System.in)
            String inoutValue = sc.nextLine()

            if (inoutValue.matches(regex)) {
                return inoutValue
            }
        }
    }

    static inputDate(String regex, String message) {
        while (true) {
            Utils.clearConsole()
            System.out.println(message)
            Scanner sc = new Scanner(System.in)
            String data = sc.nextLine()

            if (data.matches(regex)) {
                return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            }
        }
    }
}
