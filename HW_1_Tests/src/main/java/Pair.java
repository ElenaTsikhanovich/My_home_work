/**
 * <h1>Класс Pair</h1>
 * Класс описывает объект, который может содержать в себе два значения любого типа.
 * А также содержит методы работы с этими элементами.
 * @author Елена Тиханович
 * @version 1.0
 * @since 10.05.2021
 */
public class Pair <T,K>{
    /**
     * Значение первого элемента обхекта
     */
    private T firstElement;
    /**
     * Значение второго элемента объекта
     */
    private K secondElement;
    /**
     * <h2>Конструктор</h2>
     * Конструктор класса Pair. Создает объект, содержащий два элемента любых типов.
     * @param firstElement
     * @param secondElement
     */
    public Pair(T firstElement, K secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }
    /**
     * <h2>Метод first</h2>
     * @return Метод возвращает первый элемент объекта
     */
    public T first(){
        return firstElement;
    }
    /**
     * <h2> Метод last </h2>
     * @return Метод возвращает второй элемент объекта
     */
    public K last(){
        return secondElement;
    }
    /**
     * <h2> Метода swap</h2>
     * @return Метод возвращает объект, в котором первый и второй элементы поменены местами
     */
    public <K,T>Pair swap(){
        return new Pair(secondElement,firstElement);
    }
    /**
     * <h2>Метод replaceFirst</h2>
     * @param newValue Метод получает на вход элемент любого типа
     * @return Метод возвращает объект, в котором первый элемент заменен на элемент,
     * переданный в параметрах.
     */
    public <U,K>Pair replaceFirst(U newValue) {
        return new Pair(newValue,secondElement);
    }
    /**
     * <h2>Метод replaceLast</h2>
     * @param newValue Метод получает на вход элемент любого типа
     * @return Метод возвращает объект, в котором второй элемент заменен на элемент,
     * переданный в параметрах.
     */
    public <T,U >Pair replaceLast(U newValue){
        return new Pair(firstElement,newValue);
    }
}