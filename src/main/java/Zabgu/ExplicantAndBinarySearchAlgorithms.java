package Zabgu;

import java.util.Arrays;
import java.util.function.BiFunction;

public class ExplicantAndBinarySearchAlgorithms {

    /**
     * Метод выполняет поиск значения из разных видов поиска:
     *                  - Последовательный поиск (sequentialSearch)
     *                  - Бинарный поиск (binarySearch)
     *                  - Последовательный поиск с функцией (sequentialSearchWithFunction)
     *                  - Интерполяционный поиск (interpolationSearch)
     * @param arraySize размерность передаваемого массива.
     */
    public static void SearchMethods(int arraySize) {

        int randomMaximum = arraySize * 100;
        int iterations = 1_000;
        int iterationsBinary = 1_000_000;

        long totalSequentialTime = 0;
        long totalBinaryTime = 0;
        long totalInterpolationTime = 0;
        long totalSequentialCustomTime = 0;

        // Генерация случайного значения для поиска
        int searchValue = PreparatoryCode.rand.nextInt(randomMaximum);

        // Генерация случайного массива
        int[] randomArray = PreparatoryCode.rand.ints(arraySize, 0, randomMaximum).toArray();

        // Генерация монотонно увеличивающегося массива
        int[] monotonicArray = new int[arraySize];
        monotonicArray[0] = PreparatoryCode.rand.nextInt(10);
        for (int i = 1; i < arraySize; i++) {
            monotonicArray[i] = monotonicArray[i - 1] + PreparatoryCode.rand.nextInt(10) + 1;
        }

        // Последовательный поиск
        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();
            sequentialSearch(randomArray, searchValue);
            long endTime = System.currentTimeMillis();
            totalSequentialTime += (endTime - startTime);
        }
        System.out.println("Последовательный поиск (суммарное время за " + iterations + " итераций): " + totalSequentialTime + " мс.");

        // Сортировка массива для бинарного поиска
        Arrays.sort(monotonicArray);

        // Бинарный поиск
        for (int i = 0; i < iterationsBinary; i++) {
            long startTime = System.currentTimeMillis();
            binarySearch(monotonicArray, searchValue);
            long endTime = System.currentTimeMillis();
            totalBinaryTime += (endTime - startTime);
        }
        System.out.println("Бинарный поиск (суммарное время за " + iterationsBinary + " итераций): " + totalBinaryTime + " мс.");

        // Последовательный поиск с функцией
        // TODO: BiFunction<Integer, Integer, Boolean>: BiFunction - принимает два аргумента и возвращает результат.
        BiFunction<Integer, Integer, Boolean> customComparator = (a, b) -> Math.abs(a - b) <= 3;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();
            sequentialSearchWithFunction(monotonicArray, searchValue, customComparator);
            long endTime = System.currentTimeMillis();
            totalSequentialCustomTime += (endTime - startTime);
        }
        System.out.println("Последовательный поиск с функцией (суммарное время за " + iterations + " итераций) " + totalSequentialCustomTime + " мс.");

        // Интерполяционный поиск
        for (int i = 0; i < iterationsBinary; i++) {
            long startTime = System.currentTimeMillis();
            interpolationSearch(monotonicArray, searchValue);
            long endTime = System.currentTimeMillis();
            totalInterpolationTime += (endTime - startTime);
        }
        System.out.println("Интерполяционный поиск (суммарное время за " + iterationsBinary + " итераций) " + totalInterpolationTime + " мс.");
    }

    /**
     * Выполняет последовательный поиск в массиве.
     * @param array массив поиска.
     * @param value искомое значение.
     * @return возвращает -1 если не найден.
     */
    public static int sequentialSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Последовательный поиск с функцией
     * @param array массив поиска.
     * @param value искомое значение.
     * @param f произвольная функция.
     * @return возвращает -1 если не найден.
     */
    public static int sequentialSearchWithFunction(int[] array, int value, BiFunction<Integer, Integer, Boolean> f) {
        for (int i = 0; i < array.length; i++) {
            if (f.apply(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Выполняет бинарный поиск в отсортированном массиве.
     * @param sortedArray массив поиска.
     * @param value искомое значение.
     * @return возвращает -1 если не найден.
     */
    public static int binarySearch(int[] sortedArray, int value) {
        // Инициализируются переменные left (начало массива) и right (конец массива)
        int left = 0;
        int right = sortedArray.length - 1;
        while (left <= right) {
            // Вычисляется средний индекс mid
            int mid = left + (right - left) / 2;
            // Сравнение значения элемента в mid с value, если совпало, возвращает mid
            if (sortedArray[mid] == value) {
                return mid;
            }
            // Сдвигает влево или в право.
            if (sortedArray[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Интерполяционный поиск
     * @param sortedArray массив поиска.
     * @param value искомое значение.
     * @return
     */
    public static int interpolationSearch(int[] sortedArray, int value) {
        int low = 0;
        int high = sortedArray.length - 1;
        int maxIterations = 100; // Ограничение числа итераций для защиты от зацикливания

        // Проверяем, что массив вообще имеет смысл для поиска
        if (sortedArray.length == 0 || value < sortedArray[low] || value > sortedArray[high]) {
            return -1; // Значение точно не может быть найдено
        }

        while (low <= high && value >= sortedArray[low] && value <= sortedArray[high]) {
            // Защита от зацикливания
            maxIterations--;
            if (maxIterations == 0) {
                return -1; // Если слишком много итераций, выходим
            }

            // Если значения на границах одинаковые, обработка специального случая
            if (sortedArray[low] == sortedArray[high]) {
                if (sortedArray[low] == value) {
                    return low; // Значение найдено
                }
                return -1; // Значение не найдено
            }

            // Интерполяционная формула для вычисления позиции
            int pos = low + ((value - sortedArray[low]) * (high - low) /
                    (sortedArray[high] - sortedArray[low]));

            // Проверяем выход за пределы массива
            if (pos < 0 || pos >= sortedArray.length) {
                return -1; // Если индекс неожиданно выходит за диапазон, выходим
            }

            // Сравниваем элемент в позиции pos с искомым значением
            if (sortedArray[pos] == value) {
                return pos; // Значение найдено
            } else if (sortedArray[pos] < value) {
                low = pos + 1; // Сдвигаем нижнюю границу
            } else {
                high = pos - 1; // Сдвигаем верхнюю границу
            }
        }

        return -1; // Если значение не найдено
    }
}