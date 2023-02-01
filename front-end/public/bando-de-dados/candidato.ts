import { CandidatoUsuario } from "../classes/CandidatoUsuario"
import { IUsuario } from "./IUsuario"

export class BancoCandidato implements IUsuario {
    add(candidato: CandidatoUsuario) {
        const local = localStorage.getItem("candidatos")

        if(local) {
            const candidatos = JSON.parse(local)
            candidatos.push(candidato.getObject())
            localStorage.setItem("candidatos", JSON.stringify(candidatos))
        } else {
            localStorage.setItem("candidatos", JSON.stringify([candidato]))
        }
    }

    get(): [] {
        const local = localStorage.getItem("candidatos")
        if(local) {
            const candidatos = JSON.parse(local)
            return candidatos
        } else {
            return []
        }
    }

    delete(id: number): void {
        const local = localStorage.getItem("candidatos")
        if(local) {
            const candidatos = JSON.parse(local)
            candidatos.splice(id, 1)
            localStorage.setItem("candidatos", JSON.stringify(candidatos))
        } 
    }
}