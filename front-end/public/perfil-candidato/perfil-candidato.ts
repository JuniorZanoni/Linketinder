import { BancoCandidato } from "../bando-de-dados/candidato"
import { BancoEmpresa } from "../bando-de-dados/empresa"
import { CandidatoUsuario } from "../classes/CandidatoUsuario"
import { EmpresaUsuario } from "../classes/EmpresaUsuario"

const id: number = parseInt(window.location.href.split("id=")[1])

const empresas: EmpresaUsuario[] = new BancoEmpresa().get()
const candidatos: CandidatoUsuario[] = new BancoCandidato().get()
const lista = document.getElementById("lista-empresas")

if(id >= 0 && candidatos.length > 0) {
    empresas.forEach((empresa) => {
        const itemLista = document.createElement("li")
    
        let afinidade = 0
        const skillsEmpresa = empresa.skills
        const skillsCandidato = candidatos[id].skills
        skillsCandidato.forEach((e) => {
            if(skillsEmpresa.includes(e)){
                afinidade++
            }
        })
        afinidade = (afinidade * 100) / skillsEmpresa.length
    
        itemLista.innerText = `${empresa.jobName} - afinidade: ${Math.round(afinidade)}%`
        itemLista.title = `Descrição: ${empresa.description}\nSkills: ${empresa.skills}`
        lista?.appendChild(itemLista)
    })
}

const btnApagar = document.getElementById("apagar-candidato")
btnApagar?.addEventListener("click", () => {
   new BancoCandidato().delete(id)
   window.location.href = "http://localhost:9000"
})