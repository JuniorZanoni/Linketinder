package view.empresa.vaga

import model.DBConnection
import model.ModelEmpresa
import model.ModelVaga
import model.modelCompetencia.ModelCompetenciaCandidato
import model.modelCompetencia.ModelCompetenciaVaga
import service.user.empresa.Empresa
import service.vaga.Vaga
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.competencia.Competencia

class CreateVaga {

    Empresa empresa
    Integer idEmpresa = new ModelEmpresa(DBConnection.getDBConnection()).getId(empresa)

    CreateVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        String name = Input.create(Regex.descricao, "Digite o nome da vaga.")
        String description = Input.create(Regex.descricao, "Digite a decrição da vaga.")
        String local = Input.create(Regex.descricao, "Digite o local da vaga.")

        Vaga vaga = new Vaga(name, description, local)
        new ModelVaga(DBConnection.getDBConnection()).save(vaga, idEmpresa)

        ClearConsole.clear()
        Integer idVaga = new ModelVaga(DBConnection.getDBConnection()).getId(vaga, idEmpresa)
        Competencia.menu(idVaga, new ModelCompetenciaVaga(DBConnection.getDBConnection()))
    }
}
