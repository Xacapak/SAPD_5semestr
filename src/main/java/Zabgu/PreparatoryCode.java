package Zabgu;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class PreparatoryCode {

    public static final Random rand = new Random();

    /**
     * Шаблон кода для измерения времени работы участка кода
     * Instant start - текущее время в момент вызова метода now().
     * Instant finish - конечное время окончания метода.
     * Метод Duration.between() принимает два объекта типа Instant и возвращает объект Duration,
     * представляющий разницу между ними.
     * Затем, используя метод toMillis(), преобразуем Duration в миллисекунды.
     */
    public static void timeMeasurements() {
        Instant start = Instant.now();

        Instant finish = Instant.now();

        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени, мс: " + elapsed);
    }

    //TODO: 1) Передать максимальное значение для массива.
    // TODO: 2) Сделать шаблонным методом.
    /**
     * Метод шаблонный для генерации массива случайных чисел.
     * @param arraySize Размер массива.
     * @param randomMaximum Максимально допустимое случайное число.
     * @param <T> Тип входных параметров.
     */
    public static <T extends Number> void randomArrayNumbers(T arraySize, T randomMaximum) {
        // arraySize - длина массива, 0 - нижняя граница случайных чисел, randomMaximum - верхняя граница, toArray() - конвертирует поток случайных чисел в массив.
        // TODO: Можно сделать if для проверки входящих числовых типов и отрабатывать согласно требуемого алгоритма.
        int[] array = rand.ints(arraySize.intValue(), 0, randomMaximum.intValue() ).toArray();

//        System.out.println(Arrays.toString(array));
//        // TODO: Преобразуем массив int в объектный массив Integer, чтобы отработал метод записи в файл.
//        Integer[] objectArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
//        System.out.println(sortedArray(objectArray));
//        savingArrayToFile(objectArray, "randomArray.txt");
    }

    //TODO: 1) Передать максимальное значение для массива.
    // TODO: 2) Сделать шаблонным методом.
    /**
     * Создание массива заданного размера со случайными значениями, но монотонно возрастающими числами.
     * @param arraySize Размер массива.
     * @param randomStep Шаг для случайного значения.
     * @param <T> Тип входных параметров.
     */
    public static <T extends Number> void monotonicallyIncreasingArray(T arraySize, T randomStep) {
        // TODO: Можно сделать if для проверки входящих числовых типов и отрабатывать согласно требуемого алгоритма.
        int[] array = new int[arraySize.intValue()];
        array[0] = rand.nextInt(randomStep.intValue()); // Инициализация первого элемента массива случайным числом от 0 до 9

        // Заполняем массив монотонно возрастающими случайными числами
        // Каждый новый элемент массива (array[i]) рассчитывается как предыдущий элемент (array[i - 1]) плюс случайное число от 1 до 10.
        // Генерация случайного числа: rand.nextInt(10) возвращает случайное число в диапазоне от 0 до 9,
        // поэтому дополнительный +1 гарантирует, что число будет хотя бы на 1 больше предыдущего.
        for (int i = 1; i < arraySize.intValue(); i++) {
            array[i] = array[i - 1] + rand.nextInt(10) + 1;
        }

//        System.out.println(Arrays.toString(array));
//        // TODO: Преобразуем массив int в объектный массив Integer, чтобы отработал метод записи в файл.
//        Integer[] objectArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
//        System.out.println(sortedArray(objectArray));
//        savingArrayToFile(objectArray, "monotonicArray.txt");
    }

    // TODO: Сделать шаблонным методом.
    /**
     * Сохранение массива в файл.
     * @param array массив данных.
     * @param fileName имя файла.
     * @param <T> Тип входных параметров.
     */
    public static <T> void savingArrayToFile(T[] array, String fileName) {
        // Создает объект для записи в файл с именем указанным в параметре.
        try (FileWriter writer = new FileWriter(fileName)) {
            // преобразует массив в строку.
            writer.write(Arrays.toString(array));
            // Очищает буфер и записывает его содержимое в файл.
            writer.flush();
            System.out.println("Массив сохранен в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
        }
    }

    // TODO: Сделать шаблонным методом.
    /**
     * Проверяет отсортирован ли массив по возрастанию.
     * @param array массив данных.
     * @return возаращает true если массив отсортирован, иначе false.
     * @param <T> Тип входных параметров.
     */
    // <T extends Comparable<T>> параметр типа поддерживает сравнение с другими экземплярами его собственного типа через сопоставимый интерфейс.
    public static <T extends Comparable<T>> boolean sortedArray(T[] array)  {
        for (int i = 0; i < array.length - 1; i++) {
            // compareTo - метод сравнивает со своим параметром.
            if (array[i].compareTo(array[i + 1]) > 0) {
                // если текущий элемент больше следующего, массив не отсортирован
                return false;
            }
        }
        return true;
    }
}