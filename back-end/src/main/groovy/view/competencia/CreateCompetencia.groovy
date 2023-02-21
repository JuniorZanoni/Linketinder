package view.competencia

import model.DBConnection
import model.modelCompetencia.ModelCompetencia
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import service.competencia.Competencia

class CreateCompetencia {

    static void menu() {
        String skill = Input.create(Regex.competencia, "Digite o nome da competência.")
        Competencia competencia = new Competencia(skill)

        new ModelCompetencia(DBConnection.getDBConnection()).save(competencia)
        ClearConsole.clear()
    }

}
