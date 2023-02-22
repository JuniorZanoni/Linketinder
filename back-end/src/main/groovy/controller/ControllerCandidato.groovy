package controller

import service.ServiceCandidato

class ControllerCandidato {
    static Integer save(Map<String, String> candidatoBO) {
        return new ServiceCandidato().save(candidatoBO)
    }
}
