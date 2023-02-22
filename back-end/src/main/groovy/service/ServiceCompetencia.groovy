package service

import model.competencia.DAOCompetencia
import model.competencia.DAOCompetenciaCandidato
import service.competencia.Competencia

class ServiceCompetencia {
    static boolean save(String competenciaBO) {
        Competencia competencia = new Competencia(competenciaBO)

        return new DAOCompetencia().save(competencia)
    }

    static List<Map<String, String>>getCompetenciasNoHave(Integer idUser) {
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
