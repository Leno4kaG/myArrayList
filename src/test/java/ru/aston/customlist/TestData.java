package ru.aston.customlist;

/**
 * Класс с тестовыми данными
 */
public class TestData {

    public static Cat getNewCat(int index) {
        return new Cat("Aliska" + index, 1 + index, 2.1 + index);
    }

    public static CustomArrayList<Cat> getList() {
        CustomArrayList<Cat> cats = new CustomArrayList<Cat>();
        for (int i = 0; i <= 4; i++) {
            cats.add(getNewCat(i));
        }
        return cats;
    }

    public static CustomArrayList<Cat> getListForSort() {
        CustomArrayList<Cat> cats = new CustomArrayList<Cat>();
        Cat cat = new Cat("Cat", 7, 5.9);
        Cat cat1 = new Cat("Cat1", 9, 2.9);
        Cat cat2 = new Cat("Cat2", 2, 4.9);
        Cat cat3 = new Cat("Cat3", 1, 1.9);
        Cat cat4 = new Cat("Cat4", 12, 8.9);
        return cats.listOf(cat, cat1, cat2, cat3, cat4);
    }
}
