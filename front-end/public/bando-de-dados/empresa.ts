import { EmpresaUsuario } from "../classes/EmpresaUsuario"
import { IUsuario } from "./IUsuario"

export class BancoEmpresa implements IUsuario {
    add(empresa: EmpresaUsuario) {
        const local = localStorage.getItem("empresas")

        if(local) {
            const empresas = JSON.parse(local)
            empresas.push(empresa.getObject())
            localStorage.setItem("empresas", JSON.stringify(empresas))
        } else {
            localStorage.setItem("empresas", JSON.stringify([empresa]))
        }
    }

    get(): [] {
        const local = localStorage.getItem("empresas")
        if(local) {
            const empresas = JSON.parse(local)
            return empresas
        } else {
            return []
        }
    }

    delete(id: number): void {
        const local = localStorage.getItem("empresas")
        if(local) {
            const empresas = JSON.parse(local)
            empresas.splice(id, 1)
            localStorage.setItem("empresas", JSON.stringify(empresas))
        } 
    }
}