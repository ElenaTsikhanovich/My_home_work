import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
public class DateTest {
    @ParameterizedTest
    @MethodSource("dateProvider")
    public void validPrintNextDate(Integer day, Integer month, Integer year, String result) {
        Assertions.assertEquals(result, Date.printNextDate(day,month,year));
    }
    public static Stream<Arguments> dateProvider(){
        return Stream.of(
                Arguments.arguments(31,12,1985,"1 1 1986"),
                Arguments.arguments(28,2,2020,"29 2 2020"),
                Arguments.arguments(28,2,2019,"1 3 2019"),
                Arguments.arguments(30,3,1991,"31 3 1991"),
                Arguments.arguments(31,7,1988,"1 8 1988"),
                Arguments.arguments(30,8,2020,"31 8 2020"),
                Arguments.arguments(27,3,1887,"28 3 1887"),
                Arguments.arguments(28,3,1999,"29 3 1999"),
                Arguments.arguments(29,8,2021,"30 8 2021"),
                Arguments.arguments(29,2,2020,"1 3 2020"),
                Arguments.arguments(30,9,1990,"1 10 1990")

        );
    }
}