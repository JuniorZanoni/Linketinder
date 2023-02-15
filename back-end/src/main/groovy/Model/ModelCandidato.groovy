package Model

import Usuario.Candidato
import org.codehaus.groovy.util.ListHashMap

import java.time.LocalDate

class ModelCandidato {

    Candidato get(String email, String senha) {
        def candidato

        Connection.sql.query('''SELECT * FROM candidatos WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
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

                candidato = new Candidato(nome, sobrenome, dataDeNascimento, emailCandidato, cpf, pais, cep, descricao, senhaCandidato)
            }
        }

        return candidato
    }

    void save(ListHashMap candidato) {
        Connection.sql.execute('''
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

    void update(ListHashMap candidato) {
        Connection.sql.execute('''UPDATE candidatos SET nome = ? WHERE email = ?''', [candidato.nome, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET sobrenome = ? WHERE email = ?''', [candidato.sobrenome, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET data_de_nascimento = ? WHERE email = ?''', [candidato.dataDeNascimento, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET email = ? WHERE email = ?''', [candidato.email, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET cpf = ? WHERE email = ?''', [candidato.cpf, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET pais = ? WHERE email = ?''', [candidato.pais, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET cep = ? WHERE email = ?''', [candidato.cep, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET descricao = ? WHERE email = ?''', [candidato.descricao, candidato.email])
        Connection.sql.execute('''UPDATE candidatos SET senha = ? WHERE email = ?''', [candidato.senha, candidato.email])
    }

    void delete(String email) {
        Connection.sql.execute('''DELETE FROM candidatos WHERE email = ?;''', [email])
    }

    List getMatches(String email) {
        List vagas = []

        Connection.sql.query('''
                                    SELECT vagas.id, empresas.nome AS empresa, vagas.nome AS vaga, vagas.descricao, vagas.local_vaga FROM matchs
                                        LEFT JOIN candidatos ON candidatos.id = matchs.id_candidato
                                        LEFT JOIN vagas ON vagas.id = matchs.id_vaga
                                        LEFT JOIN empresas ON empresas.id = vagas.id_empresa
                                        WHERE candidatos.email = ?;
        ''', [email]) { resultSet ->
            while (resultSet.next()) {
                Integer idVaga = resultSet.getInt('id')
                String empresa = resultSet.getString('empresa').toString()
                String vaga = resultSet.getString('vaga').toString()
                String descricao = resultSet.getString('descricao').toString()
                String local = resultSet.getString('local_vaga').toString()
                vagas.add([idVaga: idVaga, empresa: empresa, vaga: vaga, descricao: descricao, local: local])
            }
        }

        return vagas
    }
}