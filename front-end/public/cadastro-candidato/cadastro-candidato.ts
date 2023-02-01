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
})

const inputEmail: HTMLElement | null = document.getElementById("candidate-email")
inputEmail?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    emailCandidate = element.value
})

const inputPassword: HTMLElement | null = document.getElementById("candidate-password")
inputPassword?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    passwordCandidate = element.value
})

const inputFormation: HTMLElement | null = document.getElementById("candidate-formation")
inputFormation?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    formationCandidate = element.value
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

form?.addEventListener("submit", (event) => {
    event.preventDefault()
    const candidato = new CandidatoUsuario(nameCandidate, emailCandidate, passwordCandidate, formationCandidate, skills)
    const banco = new BancoCandidato()
    banco.add(candidato)
    window.location.href = "http://localhost:9000"
})