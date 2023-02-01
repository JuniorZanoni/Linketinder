import { CandidatoUsuario } from "../classes/CandidatoUsuario";
import { EmpresaUsuario } from "../classes/EmpresaUsuario";

export interface IUsuario {
    add(usuario: EmpresaUsuario | CandidatoUsuario): void
    get(): []
    delete(id: number): void
}