package service

import model.DAOCandidato
import service.user.Candidato
import utils.service.ConvertStringInLocalDate

import java.time.LocalDate

class ServiceCandidato {
    static Integer save(Map<String, String> candidatoBO) {
        LocalDate dateOfBirth = ConvertStringInLocalDate.convert(candidatoBO.dateOfBirthString)

        Candidato candidato = new Candidato(
                candidatoBO.name,
                candidatoBO.lastname,
                candidatoBO.cpf,
                candidatoBO.email,
                dateOfBirth,
                candidatoBO.country,
                candidatoBO.cep,
                candidatoBO.description,
                candidatoBO.password,
        )

        return new DAOCandidato().save(candidato)
    }

    static void delete(Candidato candidato) {
        new DAOCandidato().delete(candidato)
    }

    static void update(Candidato candidato, Integer idCandidato) {
        new DAOCandidato().update(candidato, idCandidato)
    }

    static Integer getId(Candidato candidato) {
        new DAOCandidato().getId(candidato)
    }
}
