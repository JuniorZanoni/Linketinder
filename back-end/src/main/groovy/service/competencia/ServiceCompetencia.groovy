package service.competencia

import model.competencia.DAOCompetencia
import service.competencia.Competencia

class ServiceCompetencia {
    static boolean save(String competenciaBO) {
        Competencia competencia = new Competencia(competenciaBO)

        return new DAOCompetencia().save(competencia)
    }
}
