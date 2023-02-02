import { BancoCandidato } from "../bando-de-dados/candidato"
import { CandidatoUsuario } from "../classes/CandidatoUsuario"

let nameCandidate: string
let emailCandidate: string
let passwordCandidate: string
let formationCandidate: string
let skills: string[] = []

const inputName: HTMLElement | null = document.getElementById("candidate-name")
inputName?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    nameCandidate = element.value

    if(!validaNome(nameCandidate)) {
        alert("Nome inválido")
        element.value = ""
    }
})

const inputEmail: HTMLElement | null = document.getElementById("candidate-email")
inputEmail?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    emailCandidate = element.value

    if(!validaEmail(emailCandidate)) {
        alert("E-mail inválido")
        element.value = ""
    }
})

const inputPassword: HTMLElement | null = document.getElementById("candidate-password")
inputPassword?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    passwordCandidate = element.value

    if(!validaSenha(passwordCandidate)) {
        alert("Senha inválida")
        element.value = ""
    }
})

const inputFormation: HTMLElement | null = document.getElementById("candidate-formation")
inputFormation?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    formationCandidate = element.value

    if(!validaFormacao(formationCandidate)) {
        alert("Formação inválida")
        element.value = ""
    }
})

const skillsDefault = ["java", "node", "angular", "groovy", "postgreSQL", "javascript", "typescript"]
skillsDefault.forEach((skill) => {
    const element: HTMLElement | null = document.getElementById(`candidate-${skill}`)
    element?.addEventListener("change", (event) => {
        const element = event.currentTarget as HTMLInputElement
        if(element.checked) {
            skills.push(element.value)
        } else {
            skills = skills.filter((skill) => skill != element.value)
        }
    })
})

const form: HTMLElement | null = document.getElementById("registration-candidate")


function validaNome(nome: string): boolean {
    const regex = /[A-Z][a-záàâãéèêíïóôõöúçñ]+/
    const nomes = nome.split(" ")
    let validado = true
    nomes.forEach((e) => {
        if(!regex.test(e)) {
            validado = false
        }
    })
    return validado
}

function validaEmail(email: string): boolean {
    const regex = /\b\S+@\w+.\w{2,6}(\.\w{2})?\b/g
    return regex.test(email)
}

function validaFormacao(formacao: string): boolean {
    const regex = /[A-Za-záàâãéèêíïóôõöúçñ]+/
    return regex.test(formacao)
}

function validaSenha(senha: string): boolean {
    const regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%!^&*]).{6,20}$/gm
    return regex.test(senha)
}

form?.addEventListener("submit", (event) => {
    event.preventDefault()
    const candidato = new CandidatoUsuario(nameCandidate, emailCandidate, passwordCandidate, formationCandidate, skills)
    const banco = new BancoCandidato()
    banco.add(candidato)
    window.location.href = "http://localhost:9000"
})