package service

import model.DAOMatch
import model.curtida.DAOCurtidasCandidatos
import model.curtida.DAOCurtidasVagas
import service.user.Candidato

class ServiceMatch {

    static boolean matchCandidatoWithVaga(Integer idVaga, Candidato candidato) {
        return new DAOCurtidasCandidatos().match(idVaga, candidato)
    }

    static List<Map> getMatchesCandidato(Candidato candidato) {
        return new DAOMatch().getMatchesCandidato(candidato)
    }

    static boolean matchVagaWithCandidato(Integer idVaga, Integer idCandidato) {
        return new DAOCurtidasVagas().match(idVaga, idCandidato)
    }

}
