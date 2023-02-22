package controller

import service.ServiceCompetencia

class ControllerCompetencia {
    static boolean save(String competenciaBO) {
        return new ServiceCompetencia().save(competenciaBO)
    }

    static List<Map<String, String>> getCompetenciasNoHave(Integer idUser) {
        return ServiceCompetencia.getCompetenciasNoHave(idUser)
    }

    static boolean addCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        return ServiceCompetencia.addCompetenciaCandidato(idCandidato, idCompetencia)
    }

    static List<Map<String, String>> getAllCompetenciasCandidato(Integer idCandidato) {
        return ServiceCompetencia.getAllCompetenciasCandidato(idCandidato)
    }

    static boolean removeCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        return ServiceCompetencia.removeCompetenciaCandidato(idCandidato, idCompetencia)
    }
}
