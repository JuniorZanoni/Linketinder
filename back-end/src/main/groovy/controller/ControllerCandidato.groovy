package controller

import service.ServiceCandidato
import service.user.Candidato

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/candidatos")
class ControllerCandidato extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> candidatoBO = [
                    name: req.getParameter("name"),
                    lastname: req.getParameter("lastname"),
                    dateOfBirthString: req.getParameter("dateOfBirth"),
                    email: req.getParameter("email"),
                    cpf: req.getParameter("cpf"),
                    country: req.getParameter("country"),
                    cep: req.getParameter("cep"),
                    description: req.getParameter("description"),
                    password: req.getParameter("password")
            ]
            new ServiceCandidato().save(candidatoBO)
            resp.setStatus(201)
        } catch (Exception e) {
            resp.sendError(400, "Dados inválidos.")
            println e.message
        }
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
