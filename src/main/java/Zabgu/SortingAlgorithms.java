package Zabgu;

import java.util.Arrays;

public class SortingAlgorithms {

    /**
     * Методы выполняющие сортировку массивов:
     *                      - Сортировка пузырьком (bubbleSort)
     *                      - Сортировка слиянием (mergeSort)
     *                      - Быстрая сортировка (quickSort)
     *                      - Сортировка Шелла (shellSort)
     * @param arraySizes размерность передаваемого массива.
     */
    public static void measureSortingTime(int arraySizes) {

        // TODO: Генерация случайного массива.
        int[] array = generateRandomArray(arraySizes);

        // TODO: Сортировка пузырьком
        // TODO: Метод Arrays.copyOf() создаёт новый массив и копирует в него элементы старого. Он не меняет существующий массив.
        int[] bubbleSortedArray = Arrays.copyOf(array, array.length);
        long startTime = System.currentTimeMillis();
        bubbleSort(bubbleSortedArray);
        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка пузырьком, время сортировки: " + (endTime - startTime) + " мс");

        // TODO: Сортировка слиянием
        int[] mergeSortedArray = Arrays.copyOf(array, array.length);
        startTime = System.currentTimeMillis();
        mergeSort(mergeSortedArray);
        endTime = System.currentTimeMillis();
        System.out.println("Сортировка слиянием, время сортировки: " + (endTime - startTime) + " мс");

        // TODO: Быстрая сортировка (quicksort)
        int[] quickSortedArray = Arrays.copyOf(array, array.length);
        startTime = System.currentTimeMillis();
        quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Быстрая сортировка, время сортировки: " + (endTime - startTime) + " мс");

        // TODO: Сортировка Шелла
        int[] shellSortedArray = Arrays.copyOf(array, array.length);
        startTime = System.currentTimeMillis();
        shellSort(shellSortedArray);
        endTime = System.currentTimeMillis();
        System.out.println("Сортировка Шелла, время сортировки:  " + (endTime - startTime) + " мс");

        // TODO: Стандартная сортировка Java
        int[] standardSortArray = Arrays.copyOf(array, array.length);
        startTime = System.currentTimeMillis();
        standardSort(standardSortArray);
        endTime = System.currentTimeMillis();
        System.out.println("Стандартная сортировка Java, время сортировки:  " + (endTime - startTime) + " мс");
    }

    /**
     * Метод сортировки пузырьком. Элементы массива сравниваются попарно и меняются местами,
     * если они находятся не в порядке. Этот процесс повторяется до тех пор, пока массив не станет отсортированным.
     * @param array массив целых чисел, который нужно отсортировать
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * Метод сортировки слиянием. Разделяет массив на две половины, рекурсивно
     * сортирует каждую половину, а затем сливает отсортированные половины в конечный отсортированный массив.
     * @param array массив целых чисел, который нужно отсортировать.
     */
    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;

            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }
    }

    /**
     * Вспомогательный метод для слияния двух отсортированных массивов в один.
     * @param result результирующий массив.
     * @param left массив, представляющий левую половину.
     * @param right массив, представляющий правую половину.
     */
    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    /**
     * Метод быстрой сортировки. Выбирает опорный элемент и размещает все меньшие элементы слева от опорного,
     * а большие или равные - справа. Затем рекурсивно сортирует левую и правую части.
     * @param array массив целых чисел, который нужно отсортировать.
     * @param low нижний индекс начала сортировки.
     * @param high верхний индекс конца сортировки.
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    /**
     * Вспомогательный метод для разбиения массива на части относительно опорного элемента.
     * @param array массив целых чисел, который нужно отсортировать.
     * @param low нижний индекс начала сортировки.
     * @param high верхний индекс конца сортировки.
     * @return индекс опорного элемента после разбиения
     */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Метод сортировки Шелла. Массив сортируется с использованием серии постепенно уменьшающихся интервалов,
     * что делает метод более эффективным по сравнению с сортировкой вставками.
     * @param array массив целых чисел, который нужно отсортировать.
     */
    public static void shellSort(int[] array) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > key) {

                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
    }

    /**
     * Вспомогательный метод для обмена двух элементов массива.
     * @param array массив целых чисел.
     * @param i индекс первого элемента.
     * @param j индекс второго элемента.
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Вспомогательный метод для генерации массива случайных целых чисел.
     * @param size размер создаваемого массива.
     * @return массив случайных целых чисел.
     */
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }

    /**
     * Встроенная сортировка Java (Timsort)
     * Используется встроенный метод Arrays.sort(), основанный на Timsort.
     * @param array массив целых чисел.
     */
    public static void standardSort(int[] array) {
        Arrays.sort(array);
    }
}