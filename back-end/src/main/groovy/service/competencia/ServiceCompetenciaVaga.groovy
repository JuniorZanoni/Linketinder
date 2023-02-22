package service.competencia

import model.competencia.DAOCompetenciaVaga

class ServiceCompetenciaVaga {

    static void addCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        new DAOCompetenciaVaga().add(idVaga, idCompetencia)
    }

    static List<Map> getAllCompetenciasVaga(Integer idVaga) {
        return new DAOCompetenciaVaga().getCompetencias(idVaga)
    }

    static boolean removeCompetenciaVaga(Integer idVaga, Integer idCompetencia) {
        return new DAOCompetenciaVaga().remove(idVaga, idCompetencia)
    }

    static List<Map> getCompetenciasNoHaveVaga(Integer idUser) {
        return new DAOCompetenciaVaga().getCompetenciasNoHave(idUser)
    }
}
