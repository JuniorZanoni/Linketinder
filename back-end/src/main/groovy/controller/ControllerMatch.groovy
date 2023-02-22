package controller

import service.ServiceEmpresa
import service.ServiceMatch
import service.user.Candidato

class ControllerMatch {
    static boolean matchVagaWithCandidato(Integer idVaga, Integer idCandidato) {
        return ServiceMatch.matchVagaWithCandidato(idVaga, idCandidato)
    }

    static boolean matchCandidatoWithVaga(Integer idVaga, Candidato candidato) {
        return ServiceMatch.matchCandidatoWithVaga(idVaga, candidato)
    }

    static List<Map> getMatchesVaga(Integer vagaId) {
        return ServiceEmpresa.getMatchesEmpresa(vagaId)
    }

    static List<Map> getMatchesCandidato(Candidato candidato) {
        return ServiceMatch.getMatchesCandidato(candidato)
    }
}
