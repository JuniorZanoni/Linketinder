package View.Empresa

import Service.Empresa

class MenuVaga {
    Empresa empresa

    MenuVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        while (true) {
            println "1 - Criar vaga."
            println "2 - Editar vagas."
            println "3 - Listar vagas."
            println "4 - Curtir candidatos."
            println "5 - Matches."
            println ""
            println "0 - Voltar."
            Scanner sc = new Scanner(System.in)

            switch (sc.nextLine()) {
                case "1":
                    new MenuCreateVaga(empresa).menu()
                    break
                case "2":
                    new MenuEditVaga(empresa).menu()
                    break
                case "3":
                    new MenuListVagas(empresa).menu()
                    break
                case "4":
                    new MenuCurtir(empresa).menu()
                    break
                case "5":
                    new MenuMatches(empresa).menu()
                    break
                case "0":
                    new MenuEmpresa(empresa).menu()
                    break
            }
        }
    }
}
