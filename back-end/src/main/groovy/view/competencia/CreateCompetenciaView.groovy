package view.competencia

import controller.ControllerCompetencia
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input

class CreateCompetenciaView {

    static void menu() {
        String competencia = Input.create(Regex.competencia, "Digite o nome da competĂȘncia.")
        ControllerCompetencia.save(competencia)

        ClearConsole.clear()
    }

}
