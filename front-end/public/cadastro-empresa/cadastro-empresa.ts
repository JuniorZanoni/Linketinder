import { BancoEmpresa } from "../bando-de-dados/empresa"
import { EmpresaUsuario } from "../classes/EmpresaUsuario"

let nameCompany: string
let emailCompany: string
let passwordCompany: string
let jobName: string
let jobDescription: string
let skills: string[] = []

const inputName: HTMLElement | null = document.getElementById("company-name")
inputName?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    nameCompany = element.value
})

const inputEmail: HTMLElement | null = document.getElementById("company-email")
inputEmail?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    emailCompany = element.value
})

const inputPassword: HTMLElement | null = document.getElementById("company-password")
inputPassword?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    passwordCompany = element.value
})

const inputJobName: HTMLElement | null = document.getElementById("job-name")
inputJobName?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    jobName = element.value
})

const inputFormation: HTMLElement | null = document.getElementById("job-description")
inputFormation?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    jobDescription = element.value
})

const skillsDefault = ["java", "node", "angular", "groovy", "postgreSQL", "javascript", "typescript"]
skillsDefault.forEach((skill) => {
    const element: HTMLElement | null = document.getElementById(`company-${skill}`)
    element?.addEventListener("change", (event) => {
        const element = event.currentTarget as HTMLInputElement
        if(element.checked) {
            skills.push(element.value)
        } else {
            skills = skills.filter((skill) => skill != element.value)
        }
    })
})

const form: HTMLElement | null = document.getElementById("registration-company")

form?.addEventListener("submit", (event) => {
    event.preventDefault()
    const empresa = new EmpresaUsuario(nameCompany, emailCompany, passwordCompany, jobName, jobDescription, skills)
    const banco = new BancoEmpresa()
    banco.add(empresa)
    window.location.href = "http://localhost:9000"
})