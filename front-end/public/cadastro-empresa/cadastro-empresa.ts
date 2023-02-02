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

    if(!validaNome(nameCompany)) {
        alert("Nome inválido")
        element.value = ""
    }
})

const inputEmail: HTMLElement | null = document.getElementById("company-email")
inputEmail?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    emailCompany = element.value

    if(!validaEmail(emailCompany)) {
        alert("E-mail inválido")
        element.value = ""
    }
})

const inputPassword: HTMLElement | null = document.getElementById("company-password")
inputPassword?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    passwordCompany = element.value

    if(!validaSenha(passwordCompany)) {
        alert("Senha inválida")
        element.value = ""
    }
})

const inputJobName: HTMLElement | null = document.getElementById("job-name")
inputJobName?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    jobName = element.value

    if(!validaTexto(jobName)) {
        alert("Título inválido")
        element.value = ""
    }
})

const inputDescription: HTMLElement | null = document.getElementById("job-description")
inputDescription?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    jobDescription = element.value

    if(!validaTexto(jobDescription)) {
        alert("Descrição inválida")
        element.value = ""
    }
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

function validaSenha(senha: string): boolean {
    const regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%!^&*]).{6,20}$/gm
    return regex.test(senha)
}

function validaTexto(formacao: string): boolean {
    const regex = /[A-Za-záàâãéèêíïóôõöúçñ]+/
    return regex.test(formacao)
}

const form: HTMLElement | null = document.getElementById("registration-company")

form?.addEventListener("submit", (event) => {
    event.preventDefault()
    const empresa = new EmpresaUsuario(nameCompany, emailCompany, passwordCompany, jobName, jobDescription, skills)
    const banco = new BancoEmpresa()
    banco.add(empresa)
    window.location.href = "http://localhost:9000"
})