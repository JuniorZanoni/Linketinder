package controller

import service.ServiceCurtir
import service.user.Candidato

class ControllerCurtir {
    static void curtirVaga(Integer idVaga, Candidato candidato) {
        ServiceCurtir.curtirVaga(idVaga, candidato)
    }

    static boolean curtirCandidato(Integer idVaga, Integer idCandidato) {
        return ServiceCurtir.curtirCandidato(idVaga, idCandidato)
    }
}
