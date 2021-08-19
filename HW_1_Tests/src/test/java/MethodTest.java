import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashMap;
import java.util.stream.Stream;
public class MethodTest {
    @ParameterizedTest
    @DisplayName("Проверка метода arrayToMap")
    @MethodSource("mapProvider")
    public void validArrayToMap(Object[]ks, HashMap<Object,Integer> map){
        Assertions.assertEquals(map,Method.arrayToMap(ks));
    }
    public static Stream<Arguments> mapProvider(){
        String[]stringArray={"mike","lena","mike","andrey","david","masha","mike","lena"};
        final HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("mike",3);
        stringIntegerHashMap.put("lena",2);
        stringIntegerHashMap.put("andrey",1);
        stringIntegerHashMap.put("david",1);
        stringIntegerHashMap.put("masha",1);
        Integer[]integersArray={1,5,7,3,1,5,3,1,7,9,0};
        HashMap<Integer,Integer> integerIntegerHashMap=new HashMap<>();
        integerIntegerHashMap.put(1,3);
        integerIntegerHashMap.put(5,2);
        integerIntegerHashMap.put(7,2);
        integerIntegerHashMap.put(3,2);
        integerIntegerHashMap.put(9,1);
        integerIntegerHashMap.put(0,1);
        return Stream.of(
                Arguments.arguments(stringArray,stringIntegerHashMap),
                Arguments.arguments(integersArray,integerIntegerHashMap)
        );
    }
}