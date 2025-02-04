package Zabgu;

/**
 * Класс Stack реализует абстрактный тип данных "стек" (LIFO — последний вошел, первый вышел).
 *
 * @param <T> Тип данных, который будет храниться в стеке.
 */
public class Stack<T> {
    private Node<T> top;    // Ссылка на верхний элемент стека
    private int size;       // Текущий размер стека

    /**
     * Вспомогательный внутренний класс для представления узла стека.
     *
     * @param <T> Тип данных, содержащийся в узле.
     */
    private static class Node<T> {
        private T data;         // Данные, которые хранятся в узле
        private Node<T> next;   // Ссылка на следующий узел

        /**
         * Конструктор для создания узла с заданными данными.
         *
         * @param data Данные, которые необходимо поместить в узел.
         */
        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Конструктор по умолчанию. Создает пустой стек.
     */
    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Добавляет (кладет) элемент на вершину стека.
     *
     * @param item Элемент, который необходимо добавить в стек.
     */
    public void push(T item) {
        Node<T> t = new Node<>(item);   // Создаем новый узел
        t.next = top;                   // Новый узел указывает на текущий верхний элемент
        top = t;                        // Новый узел становится вершиной стека
        size++;                         // Увеличиваем размер стека
    }

    /**
     * Удаляет и возвращает элемент с вершины стека.
     *
     * @return Элемент, находящийся на вершине стека.
     * @throws IllegalStateException Если стек пустой.
     */
    public T pop() {
        if (top == null) throw new IllegalStateException("Стек пустой");    // Исключение при попытке доступа к пустому стеку
        T item = top.data;                                                  // Сохраняем данные верхнего элемента
        top = top.next;                                                     // Сдвигаем вершину на следующий элемент
        size--;                                                             // Уменьшаем размер стека
        return item;                                                        // Возвращаем данные верхнего элемента
    }

    /**
     * Возвращает элемент с вершины стека, не удаляя его.
     *
     * @return Элемент, находящийся на вершине стека.
     * @throws IllegalStateException Если стек пустой.
     */
    public T peek() {
        if (top == null) throw new IllegalStateException("Стек пустой");    // Исключение при попытке доступа к пустому стеку
        return top.data;                                                    // Возвращаем данные верхнего элемента
    }

    /**
     * Проверяет, пуст ли стек.
     *
     * @return {@code true}, если стек пустой; {@code false} — в противном случае.
     */
    public boolean isEmpty() {
        return top == null;     // Если вершина равна null, стек пустой
    }

    /**
     * Возвращает количество элементов в стеке.
     *
     * @return Текущее количество элементов в стеке.
     */
    public int size() {
        return size;
    }
}