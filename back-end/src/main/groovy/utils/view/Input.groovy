package utils.view


import utils.service.Regex

class Input {
    static String create(String regex, String message) {
        ClearConsole.clear()

        boolean condition = true
        while (condition) {
            println message
            Scanner sc = new Scanner(System.in)
            String value = sc.nextLine()

            if(Regex.verify(value, regex)) {
                return value
            }

            ClearConsole.clear()
            println "Tente novamente."
            println ""
        }
    }
}
