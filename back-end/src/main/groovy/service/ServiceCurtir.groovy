package service


import model.curtida.DAOCurtidasCandidatos
import model.curtida.DAOCurtidasVagas
import service.user.Candidato

class ServiceCurtir {

    static void curtirVaga(Integer idVaga, Candidato candidato) {
        new DAOCurtidasCandidatos().curtirVaga(idVaga, candidato)
    }

    static boolean curtirCandidato(Integer idVaga, Integer idCandidato) {
        return new DAOCurtidasVagas().curtiCandidato(idVaga, idCandidato)
    }
}
