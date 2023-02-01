import { BancoCandidato } from "../bando-de-dados/candidato"
import { BancoEmpresa } from "../bando-de-dados/empresa"
import { CandidatoUsuario } from "../classes/CandidatoUsuario"
import { EmpresaUsuario } from "../classes/EmpresaUsuario"

let login: string
let password: string

const inputLogin: HTMLElement | null = document.getElementById("login")
inputLogin?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    login = element.value
})

const inputPassword: HTMLElement | null = document.getElementById("password")
inputPassword?.addEventListener("change", (event) => {
    const element = event.currentTarget as HTMLInputElement
    password = element.value
})

const loginForm: HTMLElement | null = document.getElementById("form-login")
loginForm?.addEventListener("submit", (event) => {
    event.preventDefault()
    
    const candidatos: CandidatoUsuario[] = new BancoCandidato().get()
    const candidato = candidatos.find((e) => e.email == login && e.password == password)

    const empresas: EmpresaUsuario[] = new BancoEmpresa().get()
    const empresa = empresas.find((e) => e.email == login && e.password == password)

    if(candidato) {
        candidatos.forEach((e, i) => {
            if(e.email == candidato.email) {
                window.location.href = `http://localhost:9000/perfil-candidato/perfil-candidato.html?id=${i}`
            }
        })
    } else if (empresa) {
        empresas.forEach((e, i) => {
            if(e.email == empresa.email) {
                window.location.href = `http://localhost:9000/perfil-empresa/perfil-empresa.html?id=${i}`
            }
        })
    } else {
        alert("e-mail ou senha incorreto")
        window.location.href = "http://localhost:9000"
    }

})

const btnCadastrarCandidato: HTMLElement | null = document.getElementById("btn-cadastrar-candidato")
btnCadastrarCandidato?.addEventListener("click", (event) => {
    window.location.href = "http://localhost:9000/cadastro-candidato/cadastro-candidato.html"
})

const btnCadastrarEmpresa: HTMLElement | null = document.getElementById("btn-cadastrar-empresa")
btnCadastrarEmpresa?.addEventListener("click", (event) => {
    window.location.href = "http://localhost:9000/cadastro-empresa/cadastro-empresa.html"
})