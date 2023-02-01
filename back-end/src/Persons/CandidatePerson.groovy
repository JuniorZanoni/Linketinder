package Persons

class CandidatePerson extends Person {
    String cpf
    Integer age

    CandidatePerson(
            String name,
            String email,
            String state,
            String cep,
            String description,
            List<Skills> skills,
            String cpf,
            Integer age) {
        super(name, email, state, cep, description, skills)
        this.cpf = cpf
        this.age = age
    }
}
