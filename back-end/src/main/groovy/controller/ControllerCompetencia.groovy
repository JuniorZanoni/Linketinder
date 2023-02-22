package controller

import service.competencia.ServiceCompetencia
import service.competencia.ServiceCompetenciaCandidato
import service.competencia.ServiceCompetenciaVaga

class ControllerCompetencia {
    static boolean save(String competenciaBO) {
        return new ServiceCompetencia().save(competenciaBO)
    }

    static boolean addCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        return ServiceCompetenciaCandidato.addCompetenciaCandidato(idCandidato, idCompetencia)
    }

    static void addCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        ServiceCompetenciaVaga.addCompetenciaVaga(idVaga, idCompetencia)
    }

    static boolean removeCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        return ServiceCompetenciaCandidato.removeCompetenciaCandidato(idCandidato, idCompetencia)
    }

    static boolean removeCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        return ServiceCompetenciaVaga.removeCompetenciaVaga(idVaga, idCompetencia)
    }

    static List<Map> getCompetenciasNoHaveCandidato(Integer idUser) {
        return ServiceCompetenciaCandidato.getCompetenciasNoHaveCandidato(idUser)
    }

    static List<Map> getCompetenciasNoHaveVaga(Integer idUser) {
        return ServiceCompetenciaVaga.getCompetenciasNoHaveVaga(idUser)
    }
    
    static List<Map> getAllCompetenciasCandidato(Integer idCandidato) {
        return ServiceCompetenciaCandidato.getAllCompetenciasCandidato(idCandidato)
    }

    static List<Map> getAllCompetenciasVaga(Integer idVaga) {
        return ServiceCompetenciaVaga.getAllCompetenciasVaga(idVaga)
    }
}
