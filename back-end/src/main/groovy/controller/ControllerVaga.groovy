package controller

import service.ServiceVaga
import service.user.Candidato
import service.user.Empresa
import service.vaga.Vaga

class ControllerVaga {
    static Integer save(Vaga vaga, Integer idEmpresa) {
        ServiceVaga.save(vaga, idEmpresa)
    }

    static void update(Vaga vaga, Integer idVaga) {
        ServiceVaga.update(vaga, idVaga)
    }

    static void delete(Integer idVaga) {
        ServiceVaga.delete(idVaga)
    }

    static List<Map> getVagasByCandidato(Candidato candidato) {
        return ServiceVaga.getVagasByCandidato(candidato)
    }

    static List<Map> getVagasByEmpresa(Integer idEmpresa) {
        return ServiceVaga.getVagasByEmpresa(idEmpresa)
    }

    static Integer getIdVaga(Vaga vaga, Integer idEmpresa) {
        return ServiceVaga.getIdVaga(vaga, idEmpresa)
    }

    static List<Map> getAllVagasByEmpresaWithID(Integer idEmpresa) {
        return ServiceVaga.getAllVagasByEmpresaWithID(idEmpresa)
    }

    static boolean verifyVagaID(Empresa empresa, Integer idVaga) {
        ServiceVaga.verifyVagaID(empresa, idVaga)
    }

    static List<Map> getCandidatosAvailableByVaga(Integer idVaga) {
        return ServiceVaga.getCandidatosAvailableByVaga(idVaga)
    }

    static List<Map> getVagasByEmpresaWithID(Integer idEmpresa) {
        ServiceVaga.getVagasByEmpresaWithID(idEmpresa)
    }
}
