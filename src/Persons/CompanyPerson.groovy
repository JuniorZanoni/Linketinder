package Persons

class CompanyPerson extends Person {
    String cnpj
    String country

    CompanyPerson(
            String name,
            String email,
            String state,
            String cep,
            String description,
            List<Skills> skills,
            String cnpj,
            String country) {
        super(name, email, state, cep, description, skills)
        this.cnpj = cnpj
        this.country = country
    }

    @Override
    String toString() {
        return "${name} - ${description} - ${skills}"
    }
}
