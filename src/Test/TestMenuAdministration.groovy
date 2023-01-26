package Test

import Database.CandidateData
import Persons.CandidatePerson
import org.junit.Test
import static org.junit.Assert.*

class TesteMenuAdministration {

    @Test
    void deveCriarUmNovoCandidatoNaListaDeCandidatos() {

        List<CandidatePerson> list = CandidateData.candidates
        CandidatePerson candidate = new CandidatePerson(
                "Júnior",
                "zanoni@gmail.com",
                "RS", "95520-000",
                "Desenvolvedor Web Full Stack",
                ["JAVA", "NODE"],
                "000.000",
                27
        )


        list.add(candidate)
        CandidatePerson expect = CandidateData.candidates[- 1]

        assertEquals(expect, candidate)
        println "Criado com sucesso!"
    }

}
