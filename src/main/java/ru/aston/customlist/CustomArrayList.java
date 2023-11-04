package ru.aston.customlist;


import ru.aston.customlist.exception.CustomArrayListException;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Мой ArrayList
 *
 * @param <E> любой тип
 */

public class CustomArrayList<E> implements CustomList<E> {

    /**
     * Вместимость массива
     */
    private int capacity = 0;

    /**
     * Размер массива
     */
    private int size = 0;

    /**
     * Вместимость списка по умолчанию
     */
    private final static int DEFAULT_CAPACITY = 10;

    /**
     * Массив
     */
    private Object[] items;

    /**
     * Конструктор
     */
    public CustomArrayList() {
        capacity = DEFAULT_CAPACITY;
        items = new Object[capacity];
    }

    /**
     * Конструктор
     *
     * @param capacity исходная вместимость
     */
    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean isNotEmpty() {
        return size > 0;
    }


    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        if (size >= capacity) {
            resize();
        }
        items[size++] = e;
        return true;
    }


    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new CustomArrayListException("Индекса " + index + " не найден!!");
        }
        resize();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        ++size;
    }

    /**
     * Вспомогательный метод для изменения размера текущего массива
     */
    private void resize() {
        if (size >= capacity) {
            Object[] newItems = new Object[size * 3 / 2 + 1];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        if (size >= DEFAULT_CAPACITY && size < capacity / 4) {
            Object[] newItems = new Object[size * 3 / 2 + 1];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }


    @Override
    public E get(int index) {
        return (E) items[index];
    }


    @Override
    public void delete(int index) {
        E existObjectForDelete = (E) items[index];
        E[] temp = (E[]) items;
        items = new Object[temp.length - 1];
        System.arraycopy(temp, 0, items, 0, index);
        int amountElementAfterIndex = temp.length - index - 1;
        System.arraycopy(temp, index + 1, items, index, amountElementAfterIndex);
        size = size - index;
    }


    @Override
    public void clear() {
        items = new Object[capacity];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }


    @Override
    public E set(int index, E element) {
        if ((index < size) && (index >= 0)) {
            E e = (E) items[index];
            items[index] = element;
            return e;
        }
        return null;
    }

    /**
     * Итератор
     *
     * @return возвращает итератор для CustomArrayList
     */
    @Override
    public Iterator<E> iterator() {
        return new CustomArrayIterator<>((E[]) items);
    }


    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    public Object[] toArray() {
        return items;
    }


    @Override
    public CustomArrayList<E> listOf(E... elements) {
        items = new Object[elements.length];
        for (int i = 0; i < elements.length; i++) {
            items[i] = elements[i];
        }
        size = items.length;
        return this;
    }


    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(items, 0, items.length - 1, c);
    }

    /**
     * Реализация сортировки
     *
     * @param array массив объектов
     * @param low   первый элемент массива
     * @param high  последний элемент массива
     * @param c     реализованный Comparator
     */
    private void quickSort(Object[] array, int low, int high, Comparator<? super E> c) {
        if (array.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        Object element = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (c.compare((E) array[i], (E) element) < 0) {
                i++;
            }
            while (c.compare((E) array[j], (E) element) > 0) {
                j--;
            }

            if (i <= j) {
                Object temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(array, low, j, c);
        }

        if (high > i) {
            quickSort(array, i, high, c);
        }
    }

}
