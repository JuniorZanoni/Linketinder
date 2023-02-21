package model

import service.user.candidato.Candidato
import groovy.sql.Sql

class ModelCandidato {
    Sql connection

    ModelCandidato(Sql connection) {
        this.connection = connection
    }

    Integer getId(Candidato candidato) {
        Integer id = 0
        connection.query('''
            SELECT id FROM candidatos WHERE email = ?;''', [candidato.email]
        ) {resultSet ->
            resultSet.next()
            id = resultSet.getInt('id')
        }

        return id
    }

    Candidato getCandidato(String email, String senha) {
        Candidato candidato = null

        connection.query('''SELECT * FROM candidatos WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                String nome = resultSet.getString('nome')
                String sobrenome = resultSet.getString('sobrenome')
                Date dataDeNascimento = resultSet.getDate('data_de_nascimento')
                String emailCandidato = resultSet.getString('email')
                String cpf = resultSet.getString('cpf')
                String pais = resultSet.getString('pais')
                String cep = resultSet.getString('cep')
                String descricao = resultSet.getString('descricao')
                String senhaCandidato = resultSet.getString('senha')

                candidato = new Candidato(nome, sobrenome, cpf, emailCandidato, dataDeNascimento.toLocalDate(), pais, cep, descricao, senhaCandidato)
            }
        }

        return candidato
    }

    void save(Candidato candidato) {
        connection.execute('''
            INSERT INTO candidatos (nome, sobrenome, data_de_nascimento, email, cpf, pais, cep, descricao, senha)
                            VALUES (?,?,?,?,?,?,?,?,?)''',
                [
                        candidato.name,
                        candidato.lastname,
                        candidato.dateOfBirth,
                        candidato.email,
                        candidato.cpf,
                        candidato.country,
                        candidato.cep,
                        candidato.description,
                        candidato.password
                ]
        )
    }

    void delete(Candidato candidato) {
        connection.execute('''DELETE FROM candidatos WHERE email = ?;''', [candidato.email])
    }

    void update(Candidato candidato, Integer idCandidato) {
        connection.execute('''UPDATE candidatos SET nome = ? WHERE id = ?''', [candidato.name, idCandidato])
        connection.execute('''UPDATE candidatos SET sobrenome = ? WHERE id = ?''', [candidato.lastname, idCandidato])
        connection.execute('''UPDATE candidatos SET data_de_nascimento = ? WHERE id = ?''', [candidato.dateOfBirth, idCandidato])
        connection.execute('''UPDATE candidatos SET email = ? WHERE id = ?''', [candidato.email, idCandidato])
        connection.execute('''UPDATE candidatos SET cpf = ? WHERE id = ?''', [candidato.cpf, idCandidato])
        connection.execute('''UPDATE candidatos SET pais = ? WHERE id = ?''', [candidato.country, idCandidato])
        connection.execute('''UPDATE candidatos SET cep = ? WHERE id = ?''', [candidato.cep, idCandidato])
        connection.execute('''UPDATE candidatos SET descricao = ? WHERE id = ?''', [candidato.description, idCandidato])
        connection.execute('''UPDATE candidatos SET senha = ? WHERE id = ?''', [candidato.password, idCandidato])
    }
}
