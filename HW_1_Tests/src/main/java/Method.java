
import java.util.HashMap;
import java.util.Map;

/**
 * <h1> Класс Method </h1>
 * Класс Method содержит метод arrayToMap, который подсчитывает,
 * сколько раз в заданном массиве встречается каждый элемент.
 * @author Елена Тиханович
 * @version 1.0
 * @since 12.05.21
 */
public class Method {
    private  Method(){

    }
    /**
     * <h2> Метод arrayToMap</h2>
     * @param ks На вход метод получает массив элементов любого типа.
     * @return Метод возвращает объект HashMap,
     * где key - это элемент массива,
     * а value - это колличество его вхождений в массив.
     */
    public static <K> Map<K,Integer> arrayToMap(K[] ks){
        HashMap<K,Integer> arrayMap =new HashMap<>();
        for(int i=0;i< ks.length;i++){
            K value=ks[i];
            Integer frequency=arrayMap.get(value);
            arrayMap.put(value,frequency==null?1:frequency+1);
        }
        return arrayMap;
    }
}
