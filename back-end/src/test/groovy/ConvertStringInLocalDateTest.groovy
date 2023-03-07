import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.service.ConvertStringInLocalDate

import java.time.LocalDate

class ConvertStringInLocalDateTest {

    @Test
    void deveConveterUmaStringEmLocalDate() {
        LocalDate result = ConvertStringInLocalDate.convert("18/02/2023")
        LocalDate expected = LocalDate.parse("2023-02-18")

        Assertions.assertEquals(expected, result)
    }

}
