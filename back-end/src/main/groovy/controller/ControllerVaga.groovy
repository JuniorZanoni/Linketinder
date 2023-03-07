package controller

import service.ServiceVaga
import service.user.Candidato
import service.user.Empresa
import service.vaga.Vaga

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/vagas")
class ControllerVaga extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> vagaBO = [
                    name: req.getParameter("name"),
                    description: req.getParameter("description"),
                    local: req.getParameter("local"),
                    idEmpresa: req.getParameter("idEmpresa")
            ]

            ServiceVaga.save(vagaBO)
            resp.setStatus(201)
        } catch (Exception e) {
            resp.sendError(400, "Dados inválidos.")
            println e.message
        }
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
