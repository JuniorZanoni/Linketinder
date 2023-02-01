import { Usuario } from "./Usuario";

export class EmpresaUsuario extends Usuario {
    
    constructor(
        public name: string,
        public email: string,
        public password: string,
        public jobName: string,
        public description: string,
        public skills: string[]
        ) {
        super(name, email,password, skills)
        }

    getObject() {
        return {
            name: this.name, 
            email: this.email, 
            password: this.password,
            jobName: this.jobName, 
            description: this.description, 
            skills: this.skills}
    }
}