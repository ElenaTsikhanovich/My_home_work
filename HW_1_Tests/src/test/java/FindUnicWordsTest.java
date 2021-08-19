import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
public class FindUnicWordsTest {
    @ParameterizedTest
    @DisplayName("Проверка строки на колличество уникальных слов")
    @MethodSource("stringProvider")
    public void validFindUnicWords(String text,int count){
        Assertions.assertEquals(count,FindUnicWords.findUnicWords(text));
    }
    public static Stream<Arguments> stringProvider(){
        return Stream.of(
                Arguments.arguments("Lena are waiting for Masha, masha knows it, lena knows it too", 8 ),
                Arguments.arguments(" love feel together hello word ",5),
                Arguments.arguments("Hello hello Hello",1),
                Arguments.arguments(null,0)
        );
    }
}