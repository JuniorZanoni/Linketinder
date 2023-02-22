package controller

import service.ServiceCandidato
import service.user.Candidato

class ControllerCandidato {
    static Integer save(Map<String, String> candidatoBO) {
        return new ServiceCandidato().save(candidatoBO)
    }

    static void update(Candidato candidato, Integer idCandidato) {
        new ServiceCandidato().update(candidato, idCandidato)
    }

    static void delete(Candidato candidato) {
        new ServiceCandidato().delete(candidato)
    }

    static Integer getId(Candidato candidato) {
        new ServiceCandidato().getId(candidato)
    }
}
