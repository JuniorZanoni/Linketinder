import { BancoCandidato } from "../bando-de-dados/candidato"
import { BancoEmpresa } from "../bando-de-dados/empresa"
import { CandidatoUsuario } from "../classes/CandidatoUsuario"
import { EmpresaUsuario } from "../classes/EmpresaUsuario"

const id: number = parseInt(window.location.href.split("id=")[1])

const empresas: EmpresaUsuario[] = new BancoEmpresa().get()
const candidatos: CandidatoUsuario[] = new BancoCandidato().get()
const lista = document.getElementById("lista-candidatos")

if(id >= 0 && empresas.length > 0) {
    candidatos.forEach((candidato) => {
        const itemLista = document.createElement("li")
    
        let afinidade = 0
        const skillsEmpresa = empresas[id].skills
        const skillsCandidato = candidato.skills
        skillsEmpresa.forEach((e) => {
            if(skillsCandidato.includes(e)){
                afinidade++
            }
        })
        afinidade = (afinidade * 100) / skillsEmpresa.length
    
        itemLista.innerText = `${candidato.formation} - afinidade: ${Math.round(afinidade)}%`
        itemLista.title = `Skills: ${candidato.skills}`
        lista?.appendChild(itemLista)
    })
}

const btnApagar = document.getElementById("apagar-empresa")
btnApagar?.addEventListener("click", (event) => {
    event.preventDefault()
   new BancoEmpresa().delete(id)
   window.location.href = "http://localhost:9000"
})