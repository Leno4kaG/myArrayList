package ru.aston.customlist;

import java.util.Iterator;

/**
 * Реализация итератора
 *
 * @param <E> любой тип
 */
public class CustomArrayIterator<E> implements Iterator<E> {

    /**
     * элемент
     */
    private int element = 0;

    /**
     * массив
     */
    E[] items;

    /**
     * Конструктор
     *
     * @param items массив с любым типом
     */
    CustomArrayIterator(E[] items) {
        this.items = items;
    }

    /**
     * Проверяет есть ли в коллекции следующий элемент
     *
     * @return true при наличии элемента
     */
    @Override
    public boolean hasNext() {
        return element < items.length;
    }

    /**
     * Получение следующего элемента
     *
     * @return возвращает следующий элемент
     */
    @Override
    public E next() {
        return items[element++];
    }
}
