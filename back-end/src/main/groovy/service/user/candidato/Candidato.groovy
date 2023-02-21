package service.user.candidato

import java.time.LocalDate

class Candidato {
    String name, lastname, cpf, email, country, cep, description, password
    LocalDate dateOfBirth

    Candidato(String name, String lastname, String cpf, String email, LocalDate dateOfBirth, String country, String cep, String description, String password) {
        this.name = name
        this.lastname = lastname
        this.cpf = cpf
        this.email = email
        this.country = country
        this.cep = cep
        this.description = description
        this.password = password
        this.dateOfBirth = dateOfBirth
    }
}
