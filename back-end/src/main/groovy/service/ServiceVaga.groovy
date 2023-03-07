package service

import model.DAOVaga
import service.user.Candidato
import service.user.Empresa
import service.vaga.Vaga

class ServiceVaga {
    static List<Map> getVagasByCandidato(Candidato candidato) {
        new DAOVaga().getVagasByCandidato(candidato)
    }

    static List<Map> getVagasByEmpresa(Integer idEmpresa) {
        return new DAOVaga().getVagasByEmpresa(idEmpresa)
    }

    static Integer save(Map<String, String> vagaBO) {
        Vaga vaga = new Vaga(vagaBO.name, vagaBO.description, vagaBO.local)

        return new DAOVaga().save(vaga, vagaBO.idEmpresa.toInteger())
    }

    static Integer getIdVaga(Vaga vaga, Integer idEmpresa) {
        return new DAOVaga().getId(vaga, idEmpresa)
    }

    static void delete(Integer idVaga) {
        new DAOVaga().delete(idVaga)
    }

    static void update(Vaga vaga, Integer idVaga) {
        new DAOVaga().update(vaga, idVaga)
    }

    static List<Map> getAllVagasByEmpresaWithID(Integer idEmpresa) {
        return new DAOVaga().getVagasByEmpresaWithID(idEmpresa)
    }

    static boolean verifyVagaID(Empresa empresa, Integer idVaga) {
        return new DAOVaga().verifyVagaID(empresa, idVaga)
    }

    static List<Map> getCandidatosAvailableByVaga(Integer idVaga) {
        return new DAOVaga().getCandidatosAvailableByVaga(idVaga)
    }

    static List<Map> getVagasByEmpresaWithID(Integer idEmpresa) {
        return new DAOVaga().getVagasByEmpresaWithID(idEmpresa)
    }
}
