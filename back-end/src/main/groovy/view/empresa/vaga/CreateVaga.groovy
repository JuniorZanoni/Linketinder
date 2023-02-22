package view.empresa.vaga

import controller.ControllerEmpresa
import controller.ControllerVaga
import service.user.Empresa
import service.vaga.Vaga
import utils.service.Regex
import utils.view.ClearConsole
import utils.view.Input
import view.competencia.CompetenciaView

class CreateVaga {

    Empresa empresa
    Integer idEmpresa = ControllerEmpresa.getId(empresa)

    CreateVaga(Empresa empresa) {
        this.empresa = empresa
    }

    void menu() {
        String name = Input.create(Regex.descricao, "Digite o nome da vaga.")
        String description = Input.create(Regex.descricao, "Digite a decrição da vaga.")
        String local = Input.create(Regex.descricao, "Digite o local da vaga.")

        Vaga vaga = new Vaga(name, description, local)

        ClearConsole.clear()
        Integer idVaga = ControllerVaga.save(vaga, idEmpresa)
        CompetenciaView.menu(idVaga, "vaga")
    }
}
