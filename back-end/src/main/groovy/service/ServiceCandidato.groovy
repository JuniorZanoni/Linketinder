package service

import model.DAOCandidato
import service.user.candidato.Candidato
import utils.service.ConvertStringInLocalDate

import java.time.LocalDate

class ServiceCandidato {
    static boolean save(Map<String, String> candidatoBO) {
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
}
