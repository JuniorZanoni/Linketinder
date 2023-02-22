package service


import model.candidato.DAOCandidato
import model.empresa.DAOEmpresa
import service.user.Candidato
import service.user.Empresa
import service.user.User
import utils.view.ClearConsole

class ServiceLogin {
    static User login(String email, String password) {
        Candidato candidato = new DAOCandidato().getCandidato(email, password)
        Empresa empresa = new DAOEmpresa().getEmpresa(email, password)

        if(candidato) {
            return candidato
        } else if(empresa) {
            ClearConsole.clear()
            return empresa
        }
    }
}
