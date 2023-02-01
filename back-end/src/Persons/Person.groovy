package Persons

import groovy.transform.Canonical

@Canonical
abstract class Person {
    String name, email, state, cep, description
    List<Skills> skills
    List<Person> likes = []
    List<Person> matches = []

    Person(String name, String email, String state, String cep, String description, List<Skills> skills) {
        this.name = name
        this.email = email
        this.state = state
        this.cep = cep
        this.description = description
        this.skills = skills
    }
}
