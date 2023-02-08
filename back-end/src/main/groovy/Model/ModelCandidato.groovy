package Model

import Usuario.Candidato

import java.time.LocalDate

class ModelCandidato {

    void cadastrar(Candidato candidato) {
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

    String getByEmailAndPass(String email, String senha) {

        String emailRetorno = ""

        Connection.sql.query('''SELECT * FROM candidatos WHERE email = ? AND senha = ?;''', [email, senha]) { resultSet ->
            if (resultSet.next()) {
                emailRetorno = resultSet.getString('email').toString()
            }
        }

        return emailRetorno
    }

    void apagar(String email) {
        Connection.sql.execute('''DELETE FROM candidatos WHERE email = ?;''', [email])
    }

    void atualizarNome(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET nome = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarSobrenome(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET sobrenome = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarDataDeNascimento(LocalDate novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET data_de_nascimento = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarEmail(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET email = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarCpf(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET cpf = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarPais(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET pais = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarCep(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET cep = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarDescricao(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET descricao = ? WHERE email = ?;''', [novoValor, email])
    }

    void atualizarSenha(String novoValor, String email) {
        Connection.sql.execute('''UPDATE candidatos SET senha = ? WHERE email = ?;''', [novoValor, email])
    }

    List matches(String email) {
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