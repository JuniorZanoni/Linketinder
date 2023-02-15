package Menus


import Usuario.Candidato
import Usuario.Competencia
import Utils.Utils
import java.time.LocalDate

class CadastroCandidato {

    void menu() {
        Utils.clearConsole()
        Candidato candidato = createCandidato()

        candidato.save()

        Utils.clearConsole()
        adicionarCompetencia(candidato)

        MainMenu.menu()
    }

    private Candidato createCandidato() {
        String primeiroNome = Utils.inputString(Utils.regexNome, "Qual seu primeiro nome?")
        String sobrenome = Utils.inputString(Utils.regexSobrenome, "Qual seu sobrenome?")
        LocalDate dataDeNascimento = Utils.inputDate(Utils.regexDataDeNascimento, "Qual sua data de nascimento? Utilize o formato dd/mm/yyyy.")
        String email = Utils.inputString(Utils.regexEmail, "Qual seu e-mail?")
        String cpf = Utils.inputString(Utils.regexCpf, "Qual seu CPF? Digite apenas números.")
        String pais = Utils.inputString(Utils.regexPais, "Qual seu Pais?")
        String cep = Utils.inputString(Utils.regexCep, "Qual seu CEP? Digite apenas números.")
        String descricao = Utils.inputString(Utils.regexDescricao, "Escreva uma descrição sobre você.")
        String senha = Utils.inputString(Utils.regexSenha, "Digite uma senha.")

        return new Candidato(primeiroNome, sobrenome, dataDeNascimento, email, cpf, pais, cep, descricao, senha)
    }

    void adicionarCompetencia(Candidato candidato) {
        Competencia competencia = new Competencia()
        while (true) {
            println "Digite o número da competência que deseja adicionar."
            competencia.printCompetencias()
            println ""
            println "0 - Continuar para o menu."
            Scanner sc = new Scanner(System.in)

            try {
                Integer index = sc.nextInt()

                if (index == 0) {
                    Utils.clearConsole()
                    break
                } else {
                    try {
                        competencia.addCompetencia(candidato, index)
                        competencia.removeCompetenciaList(index)
                        Utils.clearConsole()
                    } catch (Exception e) {
                        Utils.clearConsole()
                        println "Você já possui essa competência."
                        println ""
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
