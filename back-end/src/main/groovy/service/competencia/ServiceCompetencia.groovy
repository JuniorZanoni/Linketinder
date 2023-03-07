package service.competencia

import model.competencia.DAOCompetencia

class ServiceCompetencia {
    static boolean save(String competenciaBO) {
        Competencia competencia = new Competencia(competenciaBO)

        return new DAOCompetencia().save(competencia)
    }
}
