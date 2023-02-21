import utils.service.Regex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegexTest {

    @Test
    void DeveRetornarTrueQuandoPassaNoRegex() {
        boolean result = Regex.verify("Júnior", Regex.nome,)
        Assertions.assertTrue(result)
    }

    @Test
    void DeveRetornarFalseQuandoNaoPassaNoRegex() {
        boolean result = Regex.verify("junior", Regex.nome,)
        Assertions.assertFalse(result)
    }

}
