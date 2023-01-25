package Utils

import Database.CandidateData
import Database.CompanyData
import Persons.CandidatePerson
import Persons.CompanyPerson
import Persons.Skills

class Utils {

    static clearConsole() {
        30.times { println ""}
    }

    static selectInteger(int maxOption) {
        Scanner sc = new Scanner(System.in)

        if(sc.hasNextInt()) {
            Integer option = sc.nextInt()

            if(option >=0 && option <= maxOption) {
                return option
            } else return -1

        } else {
            return -1
        }
    }

    static populateCandidates() {
        CandidatePerson c1 = new CandidatePerson(
                "Júnior Zanoni",
                "zanoni@gmail.com",
                "RS", "00000-000",
                "Estudante de Engenharia da Computação e participante do Acelera ZG",
                [Skills.ANGULAR, Skills.NODE],
                "000.000",
                27
        )

        CandidatePerson c2 = new CandidatePerson(
                "Roberto",
                "roberto@gmail.com",
                "RS", "00000-000",
                "Muito despojado",
                [Skills.PYTHON, Skills.NODE],
                "000.000",
                18
        )

        CandidatePerson c3 = new CandidatePerson(
                "Eva Helena",
                "helena@gmail.com",
                "RS", "00000-000",
                "Organizada",
                [Skills.ANGULAR, Skills.JAVA],
                "000.000",
                62
        )

        CandidatePerson c4 = new CandidatePerson(
                "Henrique",
                "henrique@gmail.com",
                "RS", "00000-000",
                "Resiliente",
                [Skills.ANGULAR, Skills.NODE, Skills.SPRING_FRAMEWOK],
                "000.000",
                22
        )

        CandidatePerson c5 = new CandidatePerson(
                "Sandubinha",
                "sandubinha@gmail.com",
                "RS", "95520-000",
                "Melhor dev galaxias",
                [Skills.ANGULAR, Skills.NODE, Skills.SPRING_FRAMEWOK, Skills.JAVA, Skills.PYTHON],
                "000.000",
                99
        )

        CandidateData.candidates.add(c1)
        CandidateData.candidates.add(c2)
        CandidateData.candidates.add(c3)
        CandidateData.candidates.add(c4)
        CandidateData.candidates.add(c5)
    }

    static populateCompanies() {
        CompanyPerson c1 = new CompanyPerson(
                "Sony",
                "sony@gmail.com",
                "RJ",
                "00000-000",
                "Fabricamos um pouco de cada coisa",
                [Skills.NODE, Skills.JAVA],
                "000.00",
                "Brazil"
        )

        CompanyPerson c2 = new CompanyPerson(
                "Dell",
                "dell@gmail.com",
                "RS",
                "00000-000",
                "Maior fabricante de Hardware do RS",
                [Skills.NODE, Skills.JAVA, Skills.PYTHON],
                "000.00",
                "Brazil"
        )

        CompanyPerson c3 = new CompanyPerson(
                "Apple",
                "apple@gmail.com",
                "RR",
                "00000-000",
                "Dispensa apresentações",
                [Skills.NODE],
                "000.00",
                "Brazil"
        )

        CompanyPerson c4 = new CompanyPerson(
                "ZG Soluções",
                "sony@gmail.com",
                "RA",
                "00000-000",
                "Proprietaria do melhor programa de capacitação de estagiários",
                [Skills.JAVA, Skills.SPRING_FRAMEWOK, Skills.ANGULAR],
                "030.0450.040",
                "Brazil"
        )

        CompanyPerson c5 = new CompanyPerson(
                "Mercado Livre",
                "sony@gmail.com",
                "RJ",
                "000.00",
                "Muita tecnologia",
                [Skills.NODE, Skills.JAVA],
                "030.0450.040",
                "Brazil"
        )

        CompanyData.companies.add(c1)
        CompanyData.companies.add(c2)
        CompanyData.companies.add(c3)
        CompanyData.companies.add(c4)
        CompanyData.companies.add(c5)

    }
}
