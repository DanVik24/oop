public class Main {
    public static void main(String[] args) {
        // Исходные данные прямо в коде (без ввода с консоли)
        String sentence = "Du, du hast, du hast mich Du, du hast, du hast mich Du, du hast, du hast mich Du, du hast, du hast mich Du, du hast, du hast mich, du hast mich Du hast mich gefragt, du hast mich gefragt Du hast mich gefragt und ich hab nichts gesagt!"; 

        // Создаём объект и передаём предложение в конструктор
        SentenceAnalyzer analyzer = new SentenceAnalyzer(sentence);

        // Получаем результат и печатаем его в main()
        String[] repeated = analyzer.getRepeatedWordsArray();
        System.out.println("Предложение: " + sentence);
        System.out.println("Количество повторяющихся слов: " + analyzer.getRepeatedWordsCount());
        System.out.print("Повторяющиеся слова (от большего числа повторов к меньшему): ");
        for (String w : repeated) {
            System.out.print(w + " ");
        }
        System.out.println();
    }
}
