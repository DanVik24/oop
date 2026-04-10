import java.util.ArrayList;

public class SentenceAnalyzer {
    private String sentence;   //ХРАНЕНИЕ ПРЕДЛОЖЕНИЯ
    private String[] repeatedWords;   // массив повторяющихся слов (отсортирован)
    private int[] wordCounts;         // соответствующие им частоты

    // Конструктор
    public SentenceAnalyzer(String sentence) {
        this.sentence = sentence;
        analyze();
    }

    // Главный метод анализа
    private void analyze() {
        // 1. Разбиваем предложение на слова по пробелам
        String[] rawWords = sentence.split(" ");

        // 2. Очищаем каждое слово от знаков препинания и приводим к нижнему регистру
        String[] cleanWords = new String[rawWords.length]; // создание массива 
        for (int i = 0; i < rawWords.length; i++) {  //цикл по словам
            cleanWords[i] = cleanWord(rawWords[i]); // очистка слов
        }

        // 3. Считаем частоту каждого уникального слова
        //    два параллельных списка: слова и их счётчики
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> frequencies = new ArrayList<>();

        for (String word : cleanWords) {
            if (word.isEmpty()) continue;

            int index = uniqueWords.indexOf(word);
            if (index == -1) {
                // новое слово
                uniqueWords.add(word);
                frequencies.add(1);
            } else {
                // слово уже есть, увеличиваем счётчик
                frequencies.set(index, frequencies.get(index) + 1);
            }
        }

        // 4. Отбираем только повторяющиеся (частота > 1)
        ArrayList<String> tempRepeated = new ArrayList<>();
        ArrayList<Integer> tempCounts = new ArrayList<>();

        for (int i = 0; i < uniqueWords.size(); i++) {
            if (frequencies.get(i) > 1) {
                tempRepeated.add(uniqueWords.get(i));
                tempCounts.add(frequencies.get(i));
            }
        }

        // 5. Сортируем повторяющиеся слова по убыванию частоты (пузырьком)
        for (int i = 0; i < tempRepeated.size() - 1; i++) {
            for (int j = 0; j < tempRepeated.size() - i - 1; j++) {
                if (tempCounts.get(j) < tempCounts.get(j + 1)) {
                    // меняем частоты
                    int tempCount = tempCounts.get(j);
                    tempCounts.set(j, tempCounts.get(j + 1));
                    tempCounts.set(j + 1, tempCount);
                    // меняем слова
                    String tempWord = tempRepeated.get(j);
                    tempRepeated.set(j, tempRepeated.get(j + 1));
                    tempRepeated.set(j + 1, tempWord);
                }
            }
        }

        // 6. Сохраняем результат в поля класса
        repeatedWords = new String[tempRepeated.size()];
        wordCounts = new int[tempRepeated.size()];
        for (int i = 0; i < tempRepeated.size(); i++) {
            repeatedWords[i] = tempRepeated.get(i);
            wordCounts[i] = tempCounts.get(i);
        }
    }

    // Вспомогательный метод: убираем знаки препинания и переводим в нижний регистр
    private String cleanWord(String word) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // оставляем только буквы (русские/английские)
            if (Character.isLetter(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    // Возвращает массив повторяющихся слов (отсортированный по убыванию частоты)
    public String[] getRepeatedWordsArray() {
        return repeatedWords;
    }

    // Возвращает количество повторяющихся слов
    public int getRepeatedWordsCount() {
        return repeatedWords.length;
    }
}
