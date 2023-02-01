export abstract class Usuario {
    
    constructor(
        public name: string,
        public email: string,
        public password: string,
        public skills: string[]
        ) {}

    abstract getObject(): void
}