package Menus

import Database.CandidateData
import Database.CompanyData
import Persons.CandidatePerson
import Persons.CompanyPerson
import Persons.Skills
import Utils.Utils

class MenuAdministration {

    void menu() {
        println "0 - Listar todos candidatos"
        println "1 - Listar todas empresas"
        println "2 - Cadastrar candidato"
        println "3 - Cadastrar empresa"
        println "4 - Voltar"

        Integer option = Utils.selectInteger(4)

        switch (option) {
            case 0:
                menuListAllCandidates()
                break
            case 1:
                menuListAllCompanies()
                break
            case 2:
                Utils.clearConsole()
                menuRegisterCandidate()
                break
            case 3:
                Utils.clearConsole()
                menuRegisterCompany()
                break
            case 4:
                Utils.clearConsole()
                MainMenu.homeMenu()
                break
            default:
                Utils.clearConsole()
                new MenuAdministration().menu()
        }
    }

    void menuListAllCandidates() {
        List<CandidatePerson> candidates = CandidateData.candidates

        Utils.clearConsole()
        candidates.each {
            println "Nome: ${it.name} | Email: ${it.email} | Estado: ${it.state} | CEP: ${it.cep} | Descrição: ${it.description} | Skills: ${it.skills} | CPF: ${it.cpf} | Idade: ${it.age}"
        }
        println ""
        menu()
    }

    void menuListAllCompanies() {
        List<CompanyPerson> companies = CompanyData.companies

        Utils.clearConsole()
        companies.each {
            println "Nome: ${it.name} | Email: ${it.email} | Estado: ${it.state} | CEP: ${it.cep} | Descrição: ${it.description} | Skills: ${it.skills} | CNPJ: ${it.cnpj} | País: ${it.country}"
        }
        println ""
        menu()
    }

    void menuRegisterCandidate() {
        println "Qual seu nome?"
        Scanner sc = new Scanner(System.in)
        String name = sc.nextLine()

        println "Qual seu e-mail?"
        Scanner sc1 = new Scanner(System.in)
        String email = sc1.nextLine()

        println "Qual seu estado?"
        Scanner sc2 = new Scanner(System.in)
        String state = sc2.nextLine()

        println "Qual seu cep?"
        Scanner sc3 = new Scanner(System.in)
        String cep = sc3.nextLine()

        println "Qual seu cpf?"
        Scanner sc4 = new Scanner(System.in)
        String cpf = sc4.nextLine()

        Integer age = inputAge()

        println "Conte um pouco sobre você."
        Scanner sc6 = new Scanner(System.in)
        String description = sc6.nextLine()

        List<Skills> skills = inputSkills("Quais Hard Skills você tem?")

        CandidatePerson candidate = new CandidatePerson(name, email, state, cep, description, skills, cpf, age)
        CandidateData.candidates.add(candidate)

        Utils.clearConsole()
        menu()
    }

    void menuRegisterCompany() {
        println "Qual o nome da empresa?"
        Scanner sc = new Scanner(System.in)
        String name = sc.nextLine()

        println "Qual o e-mail da empresa?"
        Scanner sc1 = new Scanner(System.in)
        String email = sc1.nextLine()

        println "Qual o país da empresa?"
        Scanner sc7 = new Scanner(System.in)
        String country = sc7.nextLine()

        println "Qual o estado da empresa?"
        Scanner sc2 = new Scanner(System.in)
        String state = sc2.nextLine()

        println "Qual o cep da empresa?"
        Scanner sc3 = new Scanner(System.in)
        String cep = sc3.nextLine()

        println "Qual o cnpj da empresa?"
        Scanner sc4 = new Scanner(System.in)
        String cnpj = sc4.nextLine()

        println "Conte um pouco sobre você."
        Scanner sc6 = new Scanner(System.in)
        String description = sc6.nextLine()

        List<Skills> skills = inputSkills("Quais Hard Skills a empresa precisa?")

        CompanyPerson company = new CompanyPerson(name, email, state, cep, description, skills, cnpj, country)
        CompanyData.companies.add(company)

        Utils.clearConsole()
        menu()
    }

    private Integer inputAge() {
        Integer age = 0
        while (age <= 0) {
            println "Qual sua idade?"
            Scanner sc5 = new Scanner(System.in)
            if (sc5.hasNextInt()) {
                age = sc5.nextInt()
            }
        }

        return age
    }

    private List<Skills> inputSkills (String message) {
        Boolean condition = true;

        List<Skills> skills = []
        List<Skills> listSkills = Skills.values()

        while (condition) {
            Utils.clearConsole()
            println message
            listSkills.eachWithIndex { skill, Integer i -> println "${i} - ${skill}"}
            println "${listSkills.size()} - Nenhuma das opções"

            Integer option = Utils.selectInteger(listSkills.size())

            if(option != -1) {
                if(listSkills.size() == option) {
                    condition = false
                } else {
                    skills.add(listSkills[option])
                    listSkills.remove(option)
                }
            }
        }

        return skills
    }
}
