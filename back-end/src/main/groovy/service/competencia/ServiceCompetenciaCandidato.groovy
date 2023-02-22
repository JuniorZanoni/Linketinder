package service.competencia

import model.competencia.DAOCompetenciaCandidato

class ServiceCompetenciaCandidato {
    static List<Map<String, String>>getCompetenciasNoHaveCandidato(Integer idUser) {
        new DAOCompetenciaCandidato().getCompetenciasNoHave(idUser)
    }

    static boolean addCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        new DAOCompetenciaCandidato().add(idCandidato, idCompetencia)
    }

    static boolean removeCompetenciaCandidato(Integer idCandidato, Integer idCompetencia) {
        new DAOCompetenciaCandidato().remove(idCandidato, idCompetencia)
    }

    static List<Map<String, String>> getAllCompetenciasCandidato(Integer idCandidato) {
         new DAOCompetenciaCandidato().getCompetencias(idCandidato)
    }
}
