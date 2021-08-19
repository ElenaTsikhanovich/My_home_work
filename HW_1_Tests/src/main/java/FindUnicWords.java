import java.util.*;
/**
 * <h1> Класс FindUnicWords </h1>
 * Класс содержит метод findUnicWords,
 * который позволяет посчитать колличество уникальных слов в заданной строке.
 * @author Елена Тиханович
 * @version 1.0
 * @since 23.03.21
 */
public class FindUnicWords {
    private FindUnicWords(){

    }
    /**
     * <h2> Метод findUnicWords </h2>
     * @param str На вход метод получает строку.
     * @return Метод возвращает целочисленное значение,
     * которое соответствует колличеству уникальных слов в заданной строке.
     */
    public static Integer findUnicWords(String str) {
        int countUnicWords=0;
        if(str!=null) {
            String strNew = str.trim();
            final StringBuilder allWords = new StringBuilder();
            for (int i = 0; i < strNew.length(); i++) {
                char c = strNew.charAt(i);
                if (Character.isLetter(c)) {
                    allWords.append(c);
                } else if (c == ' ') {
                    if (allWords.charAt(allWords.length() - 1) != ' ') {
                        allWords.append(c);
                    }
                }
            }
            String allWordsNew = allWords.toString().toUpperCase();
            String[] strArr = allWordsNew.split(" ");
            HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(strArr));
            countUnicWords = uniqueWords.size();
        }
        return countUnicWords;
    }
}
