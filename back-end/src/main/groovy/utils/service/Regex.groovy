package utils.service

class Regex {
    static String nome = /[A-Z][a-zéãíóúç]+/
    static String sobrenome = /([A-Z][a-zéãíóúç]+\s?)+/
    static String email = '^(.+)@(\\S+)\\.(.+)$'
    static String cpf = '[0-9]{11}'
    static String pais = /([A-Z][a-zéãíóúç]+\s?)+/
    static String cep = "[0-9]{8}"
    static String descricao = ".+"
    static String senha = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#_!\\-])[0-9a-zA-Z$*&@#_!-]{6,}'
    static String dataDeNascimento = '^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}$'
    static String nomeEmpresa = /[A-Za-z0-9éãíóúç\-õ\s]+/
    static String cnpj = '[0-9]{14}'
    static String competencia = /([A-Za-zéãíóúç]+\s?)+/

    static boolean verify(String input, String regex) {
        return input.matches(regex)
    }
}
