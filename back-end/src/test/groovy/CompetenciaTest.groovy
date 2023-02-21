import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import service.competencia.Competencia

class CompetenciaTest {

    @Test
    void deveRetornarAListaDeCompetenciasConvertidaEmBO() {
        List competencias = [[1, "Angular"], [2, "React"]]

        List<Map> result = Competencia.convertListInBO(competencias)
        List<Map> expected = [[id:1, name:"Angular"], [id:2, name:"React"]]

        Assertions.assertEquals(expected, result)
    }

    @Test
    void deveRetornarTrueSeOIndexDoElementoExistirNaLista(){
        List<Map> competencias = [[id:"1", name:"Angular"], [id:"2", name:"React"]]

        boolean result = Competencia.checkExistsInList(competencias, "1")

        Assertions.assertTrue(result)
    }
}
