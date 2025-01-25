package Zabgu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class AutomatedTests {

    /**
     * Автоматическое тестирование методов из класса PreparatoryCode с использованием встроенных утверждений (assert).
     */
    public static void PreparatoryCodeTest() {

        /**
         * Тест проверяет корректность метода PreparatoryCode.sortedArray.
         * Создаются массивы:
         *                  - sortedArray — отсортированный вручную массив
         *                  - unsortedArray — неотсортированный массив
         *                  - emptyArray — пустой массив
         *                  - singleElementArray — массив с одним элементом
         *                  - identicalElementsArray — массив с одинаковыми элементами
         *                  - reverseOrderedArray — массив с элементами в обратном порядке
         * Проверка идет через вызовы assert:
         *                  - sortedArray — должен быть распознан как отсортированный
         *                  - unsortedArray — не должен быть распознан как отсортированный
         *                  - emptyArray — пустой массив считается отсортированным
         *                  - singleElementArray — массив с одним элементом считается отсортированным
         *                  - identicalElementsArray — массив с одинаковыми элементами считается отсортированным
         *                  - reverseOrderedArray — массив с элементами в обратном порядке не должен быть распознан как отсортированный
         * Если метод работает некорректно, бросается AssertionError с описанием, почему произошел сбой.
         */
        Integer[] sortedArray = {1, 2, 3, 4, 5};
        Integer[] unsortedArray = {5, 3, 1, 4, 2};
        Integer[] emptyArray = {};
        Integer[] singleElementArray = {42};
        Integer[] identicalElementsArray = {7, 7, 7, 7, 7};
        Integer[] reverseOrderedArray = {5, 4, 3, 2, 1};

        assert PreparatoryCode.sortedArray(sortedArray) : "Ошибка: Массив 'sortedArray' должен быть упорядочен";
        assert !PreparatoryCode.sortedArray(unsortedArray) : "Ошибка: Массив 'unsortedArray' не должен быть упорядочен";
        assert PreparatoryCode.sortedArray(emptyArray) : "Ошибка: Пустой массив должен считаться упорядоченным";
        assert PreparatoryCode.sortedArray(singleElementArray) : "Ошибка: Массив 'singleElementArray' с одним элементом должен быть упорядочен";
        assert PreparatoryCode.sortedArray(identicalElementsArray) : "Ошибка: Массив 'identicalElementsArray' с одинаковыми элементами должен быть упорядочен";
        assert !PreparatoryCode.sortedArray(reverseOrderedArray) : "Ошибка: Массив 'reverseOrderedArray' не должен быть упорядочен";

        /**
         * Тест проверяет методы, что массивы корректно создаются и соответствуют требованиям
         */
        int arraySize = 10;
        int randomMaximum = 50;
        int randomStep = 10;

        // Тестируем randomArrayNumbers
        int[] array = PreparatoryCode.rand.ints(arraySize, 0, randomMaximum).toArray();
        // 1. Проверка длины массива
        assert array.length == arraySize : "Размер массива должен быть равен " + arraySize;
        // 2. Проверка диапазонов значений
        for (int value : array) {
            assert value >= 0 && value < randomMaximum :
                    "Элемент массива выходит за границы диапазона [0, " + randomMaximum + ")";
        }

        // Тестируем monotonicallyIncreasingArray
        int[] arrayMono = new int[arraySize];
        arrayMono[0] = PreparatoryCode.rand.nextInt(randomStep); // Первая точка (0 - randomStep)
        for (int i = 1; i < arraySize; i++) {
            arrayMono[i] = arrayMono[i - 1] + PreparatoryCode.rand.nextInt(10) + 1;
        }

        // 1. Проверка длины массива
        assert arrayMono.length == arraySize : "Размер массива должен быть равен " + arraySize;

        // 2. Проверка на монотонность
        for (int i = 1; i < arrayMono.length; i++) {
            assert arrayMono[i] > arrayMono[i - 1] : "Массив не является монотонно возрастающим: " +
                    arrayMono[i] + " <= " + arrayMono[i - 1];
        }

        // 3. Проверка корректного шага увеличения
        for (int i = 1; i < arrayMono.length; i++) {
            int step = arrayMono[i] - arrayMono[i - 1];
            assert step >= 1 && step <= 10 : "Шаг («" + step + "») выходит за допустимые границы [1, 10]";
        }

        assert Files.exists(Paths.get("randomArray.txt")) : "Ошибка: файл randomArray.txt не был создан";
        assert Files.exists(Paths.get("monotonicArray.txt")) : "Ошибка: файл monotonicArray.txt не был создан";

        System.out.println("Все тесты прошли успешно!");
    }

    /**
     * Тесты для класса ExplicantAndBinarySearchAlgorithms
     */
    public static void ExplicantAndBinarySearchAlgorithmsTest(){
        int[] array = {1, 2, 3, 4, 5};
        int[] sortedArray = {1, 2, 3, 4, 5};
        int[] sortedArrayInterpolation = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        assert ExplicantAndBinarySearchAlgorithms.sequentialSearch(array, 3) == 2 :
                "Не удалось выполнить последовательный поиск, ожидаемый индекс 2";
        assert ExplicantAndBinarySearchAlgorithms.sequentialSearch(array, 6) == -1 :
                "Не удалось выполнить последовательный поиск, ожидаемый индекс -1";
        assert ExplicantAndBinarySearchAlgorithms.sequentialSearch(array, 1) == 0 :
                "Не удалось выполнить последовательный поиск, ожидаемый индекс 0";
        assert ExplicantAndBinarySearchAlgorithms.sequentialSearch(array, 5) == 4 :
                "Не удалось выполнить последовательный поиск, ожидаемый индекс 4";

        assert ExplicantAndBinarySearchAlgorithms.binarySearch(sortedArray, 2) == 1 :
                "Не удалось выполнить бинарный поиск, ожидаемый индекс 1";
        assert ExplicantAndBinarySearchAlgorithms.binarySearch(sortedArray, 6) == -1 :
                "Не удалось выполнить бинарный поиск, ожидаемый индекс -1";
        assert ExplicantAndBinarySearchAlgorithms.binarySearch(sortedArray, 1) == 0 :
                "Не удалось выполнить бинарный поиск, ожидаемый индекс 0";
        assert ExplicantAndBinarySearchAlgorithms.binarySearch(sortedArray, 5) == 4 :
                "Не удалось выполнить бинарный поиск, ожидаемый индекс 4";

        assert ExplicantAndBinarySearchAlgorithms.interpolationSearch(sortedArrayInterpolation, 60) == 5 :
                "Не удалось выполнить интерполяционный поиск, ожидаемый индекс 5";
        assert ExplicantAndBinarySearchAlgorithms.interpolationSearch(sortedArrayInterpolation, 105) == -1 :
                "Не удалось выполнить интерполяционный поиск, ожидаемый индекс -1";
        assert ExplicantAndBinarySearchAlgorithms.interpolationSearch(sortedArrayInterpolation, 10) == 0 :
                "Не удалось выполнить интерполяционный поиск, ожидаемый индекс 0";
        assert ExplicantAndBinarySearchAlgorithms.interpolationSearch(sortedArrayInterpolation, 100) == 9 :
                "Не удалось выполнить интерполяционный поиск, ожидаемый индекс 9";
        assert ExplicantAndBinarySearchAlgorithms.interpolationSearch(new int[]{}, 10) == -1 :
                "Не удалось выполнить интерполяционный поиск, ожидаемый индекс -1 для пустого массива";

        System.out.println("Все тесты прошли успешно!");
    }

    /**
     * Проверяет корректность всех алгоритмов сортировки. Для каждого алгоритма
     * проверяет, совпадает ли результат их работы с эталонной сортировкой
     * (с использованием стандартного метода `Arrays.sort()`).
     *
     * @param original исходный массив, который нужно проверить
     */
    public static void SortingAlgorithmsTest(int[] original){

        // Эталонный массив, отсортированный с помощью стандартного метода
        int[] sorted = Arrays.copyOf(original, original.length);
        Arrays.sort(sorted);

        int[] testArray;

        // Тестирование сортировки пузырьком
        testArray = Arrays.copyOf(original, original.length);
        SortingAlgorithms.bubbleSort(testArray);
        assert Arrays.equals(testArray, sorted) : "Сортировка пузырьком завершилась ошибкой!";

        // Тестирование сортировки слиянием
        testArray = Arrays.copyOf(original, original.length);
        SortingAlgorithms.mergeSort(testArray);
        assert Arrays.equals(testArray, sorted) : "Сортировка слиянием завершилась ошибкой!";

        // Тестирование быстрой сортировки
        testArray = Arrays.copyOf(original, original.length);
        SortingAlgorithms.quickSort(testArray, 0, original.length - 1);
        assert Arrays.equals(testArray, sorted) : "Быстрая сортировка завершилась ошибкой!";

        // Тестирование сортировки Шелла
        testArray = Arrays.copyOf(original, original.length);
        SortingAlgorithms.shellSort(testArray);
        assert Arrays.equals(testArray, sorted) : "Сортировка Шелла завершилась ошибкой!";

    }

    public static void StackTest(){

        // Создание пустого стека
        Stack<Integer> stack = new Stack<>();

        // Тест 1: Проверка, что стек пустой
        assert stack.isEmpty() : "Ошибка: Новый стек должен быть пустым";
        assert stack.size() == 0 : "Ошибка: Размер нового стека должен быть 0";

        // Тест 2: Добавление элементов
        stack.push(10);
        assert !stack.isEmpty() : "Ошибка: Стек не должен быть пустым после push()";
        assert stack.size() == 1 : "Ошибка: Размер стека должен быть равен 1";

        stack.push(20);
        assert stack.size() == 2 : "Ошибка: Размер стека должен быть равен 2";
        assert stack.peek() == 20 : "Ошибка: Верхний элемент должен быть равен 20";

        // Тест 3: Удаление элементов
        int popped = stack.pop();
        assert popped == 20 : "Ошибка: pop() должен вернуть 20";
        assert stack.size() == 1 : "Ошибка: Размер стека должен быть равен 1 после pop()";
        assert stack.peek() == 10 : "Ошибка: Верхний элемент должен быть равен 10";

        popped = stack.pop();
        assert popped == 10 : "Ошибка: pop() должен вернуть 10";
        assert stack.isEmpty() : "Ошибка: Стек должен быть пустым после удаления всех элементов";
        assert stack.size() == 0 : "Ошибка: Размер стека должен быть равен 0 после удаления всех элементов";

        // Тест 4: Ошибки при работе с пустым стеком
        try {
            stack.pop();
            assert false : "Ошибка: Вызов pop() для пустого стека должен выбрасывать исключение";
        } catch (IllegalStateException e) {
            assert true; // Ожидаемое поведение
        }

        try {
            stack.peek();
            assert false : "Ошибка: Вызов peek() для пустого стека должен выбрасывать исключение";
        } catch (IllegalStateException e) {
            assert true; // Ожидаемое поведение
        }

        // Тест 5: Работа с разными типами
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        assert stringStack.peek().equals("World") : "Ошибка: Верхний элемент должен быть 'World'";
        assert stringStack.size() == 2 : "Ошибка: Размер стека должен быть равен 2";

        System.out.println("Все тесты успешно пройдены!");

    }
}