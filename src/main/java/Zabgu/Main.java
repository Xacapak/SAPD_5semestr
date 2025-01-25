package Zabgu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите задание 1-5");
        int TaskNumber = scanner.nextInt();

        switch (TaskNumber) {
            case 1: {
                System.out.println("Подготовительный код.\n");

                // TODO: Отладка просмотра введенных данных.
//                System.out.println("Введите размер массива.");
//                int arraySize = scanner.nextInt();
//
//                System.out.println("Введите максимальное случайное число.");
//                int randomMaximum = scanner.nextInt();
//                PreparatoryCode.randomArrayNumbers(arraySize, randomMaximum);
//
//                System.out.println("Введите максимальный шаг случайного числа.");
//                int randomStep = scanner.nextInt();
//                PreparatoryCode.monotonicallyIncreasingArray(arraySize, randomStep);

                // TODO: Отладка тестов.
                AutomatedTests.PreparatoryCodeTest();

                break;
            }
            case 2:
                System.out.println("Алгоритмы последовательного и бинарного поиска.\n");

//                int[] arraySizes = {100, 1_000, 10_000, 100_000, 1_000_000};
//                // TODO: Отладка ввода/вывода данных.
//                for (int size : arraySizes) {
//                    System.out.println("\nРазмер массива: " + size);
//                    ExplicantAndBinarySearchAlgorithms.SearchMethods(size);
//                }

                // TODO: Отладка тестов.
                AutomatedTests.ExplicantAndBinarySearchAlgorithmsTest();

                break;
            case 3:
                System.out.println("Сортировки.\n");

                int[] arraySizes = {100, 1_000, 10_000, 100_000, 1_000_000};
                // TODO: Отладка ввода/вывода данных.
                for (int size : arraySizes){
                    System.out.println("\nРазмер массива: " + size);
                    SortingAlgorithms.measureSortingTime(size);
                }

//                // TODO: Отладка тестов.
//                AutomatedTests.SortingAlgorithmsTest(new int[] {5, 3, 8, 4, 2});        // Массив из случайных чисел
//                AutomatedTests.SortingAlgorithmsTest(new int[] {1, 2, 3, 4, 5});        // Отсортированный массив
//                AutomatedTests.SortingAlgorithmsTest(new int[] {-1, -3, 0, 2, 1, 4});   // Массив с отрицательными числами
//                AutomatedTests.SortingAlgorithmsTest(new int[] {});                     // Пустой массив
//
//                System.out.println("Все тесты прошли успешно!");

                break;

            case 4:
                System.out.println("Стек. АДТ стек. Класс Стек.\n");

                // TODO: Отладка ввода/вывода данных.
                Stack<Integer> stack = new Stack<>();
                stack.push(10);
                stack.push(20);
                stack.push(30);

                System.out.println("Максимальный элемент: " + stack.peek());
                System.out.println("Размер стека: " + stack.size());

                System.out.println("Элемент:");
                while (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }

                // TODO: Отладка тестов.
                AutomatedTests.StackTest();

                break;
            case 5:
                System.out.println("Стековый калькулятор.\n");

                break;
            default:
                System.out.println("Выбрано некорректное задание");
                break;
        }
    }
}