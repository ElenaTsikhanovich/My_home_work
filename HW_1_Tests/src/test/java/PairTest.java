import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.stream.Stream;
public class PairTest <K,T,U>{
    @ParameterizedTest
    @MethodSource("pairProvide")
    public void validFirs(K first, T second){
        Assertions.assertEquals(first,new Pair(first,second).first());
    }

    @ParameterizedTest
    @MethodSource("pairProvide")
    public void validLast(K first, T second){
        Assertions.assertEquals(second,new Pair(first,second).last());
    }

    @ParameterizedTest
    @MethodSource("pairProvide")
    public void validSwap(K first, T second){
        Assertions.assertEquals(second.getClass(),new Pair(first,second).swap().first().getClass());
        Assertions.assertEquals(first.getClass(),new Pair(first,second).swap().last().getClass());
    }
    @ParameterizedTest
    @MethodSource("elementsProvider")
    public void validReplaceFirst(K first,T second, U third){
        Assertions.assertEquals(third.getClass(),
                new Pair(first,second).replaceFirst(third).first().getClass());
    }
    @ParameterizedTest
    @MethodSource("elementsProvider")
    public void validReplaceLast(K first,T second, U third){
        Assertions.assertEquals(third.getClass(),
                new Pair(first,second).replaceLast(third).last().getClass());
    }
    public static Stream<Arguments>pairProvide(){
        return Stream.of(
                Arguments.arguments("mike",1),
                Arguments.arguments(13,1.3),
                Arguments.arguments(14,false),
                Arguments.arguments(11,"lena"),
                Arguments.arguments(LocalDate.now(),"day of")
        );
    }
    public static Stream<Arguments> elementsProvider(){
        return Stream.of(
                Arguments.arguments("agy",56,1.9),
                Arguments.arguments(678696,false,"masha"),
                Arguments.arguments(true,"kgit",1.6),
                Arguments.arguments(LocalDate.now(),"88",2.6),
                Arguments.arguments(LocalDate.now(),"goer",1985)
        );
    }
}