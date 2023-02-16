package View.Candidato

import Service.Candidato
import View.MainMenu

class MenuCandidato {
    Candidato candidato

    MenuCandidato(Candidato candidato) {
        this.candidato = candidato
    }

    void menu() {
        while (true) {
            println "1 - Editar conta."
            println "2 - Curtir."
            println "3 - Matches."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new EditCandidato(candidato).menu()
                    break
                case "2":
                    new MenuCurtir(candidato).menu()
                    break
                case "3":
                    new MenuMatches(candidato).menu()
                    break
                case "0":
                    MainMenu.main()
                    break
            }
        }
    }
}
