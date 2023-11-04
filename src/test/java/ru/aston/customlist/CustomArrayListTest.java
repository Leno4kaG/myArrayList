package ru.aston.customlist;


import org.junit.jupiter.api.Test;
import ru.aston.customlist.exception.CustomArrayListException;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест класс для проверки методов CustomArrayList
 */
public class CustomArrayListTest {

    @Test
    public void sizeWhenListIsNotEmptyReturnSizeList() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();

        //when
        int result = cats.size();

        //then
        assertEquals(5, result);
    }

    @Test
    public void sizeWhenListIsEmptyReturnSizeList() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();

        //when
        int result = cats.size();

        //then
        assertEquals(0, result);
    }

    @Test
    public void isEmptyWhenListIsNotEmptyReturnFalse() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();

        //when
        boolean result = cats.isEmpty();

        //then
        assertFalse(result);
    }

    @Test
    public void isEmptyWhenListIsEmptyReturnTrue() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();

        //when
        boolean result = cats.isEmpty();

        //then
        assertTrue(result);
    }

    @Test
    public void isNotEmptyWhenListIsNotEmptyReturnTrue() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();

        //when
        boolean result = cats.isNotEmpty();

        //then
        assertTrue(result);
    }

    @Test
    public void isNotEmptyWhenListIsEmptyReturnFalse() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();

        //when
        boolean result = cats.isNotEmpty();

        //then
        assertFalse(result);
    }

    @Test
    public void containsWhenObjectIsInListReturnTrue() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        Cat cat = new Cat("Volk", 5, 4.5);
        cats.add(cat);

        //when
        boolean result = cats.contains(cat);

        //then
        assertTrue(result);
    }

    @Test
    public void containsWhenObjectIsInListReturnFalse() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        Cat cat = new Cat("Volk", 5, 4.5);

        //when
        boolean result = cats.contains(cat);

        //then
        assertFalse(result);
    }

    @Test
    public void addWhenElementAddListReturnTrue() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        Cat cat = new Cat("Volk", 5, 4.5);

        assertEquals(0, cats.size());
        //when
        boolean result = cats.add(cat);

        //then
        assertTrue(result);
        assertEquals(1, cats.size());
    }

    @Test
    public void addElementInCycle() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        Cat cat = new Cat("Volk", 5, 4.5);
        int count = 0;

        //when
        do {
            cats.add(cat);
            count++;
        } while (count < 10000);

        //then
        assertEquals(10000, cats.size());
    }


    @Test
    public void addWhenElementAddListReturnFalse() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        //when
        boolean result = cats.add(null);

        //then
        assertFalse(result);
        assertEquals(0, cats.size());
    }

    @Test
    public void addElementWhenAddNewElementIndexList() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        Cat cat = new Cat("Volk", 5, 4.5);

        //when
        cats.add(1, cat);
        Cat result = cats.get(1);

        //then
        assertEquals(cat, result);
    }

    @Test
    public void addElementByIndexInCycle() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        Cat cat = new Cat("Volk", 5, 4.5);
        int count = 0;

        //when
        do {
            cats.add(0, cat);
            count++;
        } while (count < 10000);

        //then
        assertEquals(10000, cats.size());
    }

    @Test
    public void addElementWhenAddNewElementIndexNotFoundList() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        Cat cat = new Cat("Volk", 5, 4.5);
        String errorMessage = "Индекса 99 не найден!!";

        //when
        CustomArrayListException error = assertThrows(CustomArrayListException.class,
                () -> cats.add(99, cat));

        //then
        assertEquals(errorMessage, error.getMessage());
    }

    @Test
    public void addWhenListIsAddNullReturnNull() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();

        //when
        cats.add(1, null);
        Cat result = cats.get(1);

        //then
        assertNull(result);
    }

    @Test
    public void getWhenGetsElementIndexReturnElement() {
        //given
        CustomArrayList<Cat> cats = new CustomArrayList<>();
        Cat cat = new Cat("Volk", 5, 4.5);
        cats.add(0, cat);

        //when
        Cat result = cats.get(0);

        //then
        assertEquals(cat, result);
    }

    @Test
    public void getWhenElementIsNotFound() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        String errorMessage = "Index 99 out of bounds for length 10";

        //when
        ArrayIndexOutOfBoundsException error = assertThrows(ArrayIndexOutOfBoundsException.class, () -> cats.get(99));

        //then
        assertEquals(errorMessage, error.getMessage());
    }

    @Test
    public void deleteElement() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        assertEquals(5, cats.size());

        //when
        cats.delete(1);

        //then
        assertEquals(4, cats.size());
    }

    @Test
    public void deleteElementWhenNotFound() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        assertEquals(5, cats.size());
        String errorMessage = "Index 99 out of bounds for length 10";

        //when
        ArrayIndexOutOfBoundsException error = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> cats.delete(99));

        //then
        assertEquals(errorMessage, error.getMessage());
        assertEquals(5, cats.size());
    }

    @Test
    public void clearTest() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        assertEquals(5, cats.size());
        assertTrue(cats.isNotEmpty());

        //when
        cats.clear();

        //then
        assertEquals(0, cats.size());
        assertTrue(cats.isEmpty());
    }

    @Test
    public void setElement() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();
        Cat updateElement = cats.get(1);
        int oldAge = updateElement.getAge();
        updateElement.setAge(6);
        assertEquals(2, oldAge);

        //given
        Cat updatedCat = cats.set(1, updateElement);

        //then
        assertEquals(6, updatedCat.getAge());
        assertEquals(5, cats.size());
    }

    @Test
    public void setElementWhenNotFound() {
        //given
        CustomArrayList<Cat> cats = TestData.getList();

        //then
        assertNull(cats.set(99, new Cat("cat", 1, 2.00)));
    }

    @Test
    public void sort() {
        //given
        CustomArrayList<Cat> cats = TestData.getListForSort();

        //when
        cats.sort(Comparator.comparingInt(Cat::getAge));

        //then
        assertEquals(1, cats.get(0).getAge());
        assertEquals(2, cats.get(1).getAge());
        assertEquals(7, cats.get(2).getAge());
        assertEquals(9, cats.get(3).getAge());
        assertEquals(12, cats.get(4).getAge());
    }
}
