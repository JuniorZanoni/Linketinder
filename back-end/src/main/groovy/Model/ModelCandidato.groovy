package Model

import Service.Candidato

import java.time.LocalDate

class ModelCandidato {
    def connection

    ModelCandidato(connection) {
        this.connection = connection
    }

    Candidato load(String email, String senha) {
        def candidato

        connection.sql.query('''SELECT * FROM candidatos WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                String nome = resultSet.getString('nome').toString()
                String sobrenome = resultSet.getString('sobrenome').toString()
                LocalDate dataDeNascimento = LocalDate.parse(resultSet.getString('data_de_nascimento').toString())
                String emailCandidato = resultSet.getString('email').toString()
                String cpf = resultSet.getString('cpf').toString()
                String pais = resultSet.getString('pais').toString()
                String cep = resultSet.getString('cep').toString()
                String descricao = resultSet.getString('descricao').toString()
                String senhaCandidato = resultSet.getString('senha').toString()

                candidato = new Candidato(nome, emailCandidato, pais, cep, descricao, senhaCandidato, sobrenome, cpf, dataDeNascimento)
            }
        }

        return candidato
    }

    void save(Candidato candidato) {
        connection.sql.execute('''
            INSERT INTO candidatos (nome, sobrenome, data_de_nascimento, email, cpf, pais, cep, descricao, senha)
                            VALUES (?,?,?,?,?,?,?,?,?)''',
                [
                        candidato.nome,
                        candidato.sobrenome,
                        candidato.dataDeNascimento,
                        candidato.email,
                        candidato.cpf,
                        candidato.pais,
                        candidato.cep,
                        candidato.descricao,
                        candidato.senha
                ]
        )
    }

    void delete(Candidato candidato) {
        connection.sql.execute('''DELETE FROM candidatos WHERE email = ?;''', [candidato.email])
    }

    void update(Candidato candidato) {
        connection.sql.execute('''UPDATE candidatos SET nome = ? WHERE email = ?''', [candidato.nome, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET sobrenome = ? WHERE email = ?''', [candidato.sobrenome, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET data_de_nascimento = ? WHERE email = ?''', [candidato.dataDeNascimento, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET cpf = ? WHERE email = ?''', [candidato.cpf, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET pais = ? WHERE email = ?''', [candidato.pais, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET cep = ? WHERE email = ?''', [candidato.cep, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET descricao = ? WHERE email = ?''', [candidato.descricao, candidato.email])
        connection.sql.execute('''UPDATE candidatos SET senha = ? WHERE email = ?''', [candidato.senha, candidato.email])
    }

    void updateEmail(Candidato candidato, String email) {
        connection.sql.execute('''UPDATE candidatos SET email = ? WHERE email = ?''', [email, candidato.email])
    }
}
