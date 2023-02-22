package controller

import service.ServiceCandidato

class ControllerCandidato {
    static boolean save(Map<String, String> candidatoBO) {
        return new ServiceCandidato().save(candidatoBO)
    }
}
