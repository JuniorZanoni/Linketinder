import { Usuario } from "./Usuario";

export class CandidatoUsuario extends Usuario {
    
    constructor(
        public name: string,
        public email: string,
        public password: string,
        public formation: string,
        public skills: string[]
        ) {
        super(name, email,password, skills)
    }

    getObject() {
        return {
            name: this.name, 
            email: this.email, 
            password: this.password, 
            formation: this.formation, 
            skills: this.skills
        }
    }
}