package com.example;

import java.util.Random;

public class TestGenerator {

    public static TestCase generateTestCase(String name, int arraySize, boolean zeros, long down, long up, boolean nullable) {
        TestCase testCase = new TestCase();
        testCase.name = name;
        testCase.expectedIndex = -1;
        testCase.expectedValue = 0;
        testCase.expectedSum = 0;
        testCase.expectedCube = 0;
        testCase.expectedTransformedArray = null;

        // Проверка корректности размера массива
        if (arraySize < 0) {
            testCase.input = null;
            testCase.expectedErrors.add("Размер массива не может быть отрицательным.");
            testCase.expectedTransformedArray = null;
            return testCase;
        }

        // Проверка границ
        if (down < Integer.MIN_VALUE || up > Integer.MAX_VALUE) {
            testCase.input = new int[arraySize];
            testCase.expectedErrors.add("Элемент на индексе 0 выходит за границы типа int.");
            testCase.expectedTransformedArray = testCase.input.clone();
            return testCase;
        }

        // Проверка пустого массива или флага zeros
        if (zeros || arraySize == 0) {
            testCase.input = null;
            testCase.expectedErrors.add("Массив null или пустой.");
            testCase.expectedTransformedArray = new int[0];
            return testCase;
        }

        Random rnd = new Random(name.hashCode());
        int[] array;

        if (nullable && (name.equals("EquivalenceClass16") || name.equals("EquivalenceClass17") || name.equals("EquivalenceClass18"))) {
            array = new int[arraySize];
            if (name.equals("EquivalenceClass16")) {
                array[0] = rnd.nextInt(50) * 2 + 2;
                for (int i = 1; i < arraySize; i++) {
                    array[i] = generateRandomInRange(rnd, down, up);
                    if (array[i] > 0 && array[i] % 2 == 0) array[i]++;
                }
            } else if (name.equals("EquivalenceClass17")) {
                int mid = arraySize / 2;
                for (int i = 0; i < arraySize; i++) {
                    array[i] = generateRandomInRange(rnd, down, up);
                    if (i == mid)
                        array[i] = rnd.nextInt(50) * 2 + 2;
                    else if (array[i] > 0 && array[i] % 2 == 0)
                        array[i]++;
                }
            } else {
                array[arraySize - 1] = rnd.nextInt(50) * 2 + 2;
                for (int i = 0; i < arraySize - 1; i++) {
                    array[i] = generateRandomInRange(rnd, down, up);
                    if (array[i] > 0 && array[i] % 2 == 0) array[i]++;
                }
            }
        } else {
            try {
                array = generateArray(rnd, down, up, arraySize);
            } catch (IllegalArgumentException e) {
                testCase.input = new int[arraySize];
                testCase.expectedErrors.add("Элемент на индексе 0 выходит за границы типа int.");
                testCase.expectedTransformedArray = testCase.input.clone();
                return testCase;
            }
        }

        testCase.input = array;
        testCase.expectedTransformedArray = array.clone();

        if (array.length > 1024) {
            testCase.expectedErrors.add("Размер массива превышает максимальный предел 1024.");
            return testCase;
        }

        // Проверка элементов массива на выход за границы
        for (int i = 0; i < array.length; i++) {
            if (array[i] < Integer.MIN_VALUE || array[i] > Integer.MAX_VALUE) {
                testCase.expectedErrors.add("Элемент на индексе " + i + " выходит за границы типа int.");
                return testCase;
            }
        }

        // Вычисление суммы
        try {
            long sum = 0;
            for (int value : array) {
                sum = Math.addExact(sum, value);
            }
            testCase.expectedSum = sum;
        } catch (ArithmeticException e) {
            testCase.expectedErrors.add("Переполнение при вычислении суммы элементов массива.");
            return testCase;
        }

        // Поиск первого четного положительного числа
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && array[i] % 2 == 0) {
                testCase.expectedIndex = i;
                testCase.expectedValue = array[i];
                break;
            }
        }

        if (testCase.expectedIndex == -1) {
            testCase.expectedErrors.add("В массиве нет четных положительных чисел.");
            return testCase;
        }

        // Вычисление куба разности
        try {
            long diff = testCase.expectedValue - testCase.expectedSum;
            long cube = Math.multiplyExact(diff, Math.multiplyExact(diff, diff));
            testCase.expectedCube = cube;
        } catch (ArithmeticException e) {
            testCase.expectedErrors.add("Переполнение при вычислении куба разности.");
            testCase.expectedIndex = -1;
            testCase.expectedValue = 0;
            testCase.expectedCube = 0;
            return testCase;
        }

        if (testCase.expectedCube < Integer.MIN_VALUE || testCase.expectedCube > Integer.MAX_VALUE) {
            testCase.expectedErrors.add("Куб разности не помещается в тип int для преобразования массива.");
            testCase.expectedIndex = -1;
            testCase.expectedValue = 0;
            return testCase;
        }

        testCase.expectedTransformedArray[testCase.expectedIndex] = (int) testCase.expectedCube;

        return testCase;
    }

    private static int[] generateArray(Random rnd, long down, long up, int size) {
        if (down > up) {
            long temp = down;
            down = up;
            up = temp;
        }

        down = Math.max(down, Integer.MIN_VALUE);
        up = Math.min(up, Integer.MAX_VALUE);

        if (down > up) {
            throw new IllegalArgumentException("minValue не может быть больше maxValue.");
        }

        int[] array = new int[size];
        if (down == up) {
            for (int i = 0; i < size; i++) {
                array[i] = (int) down;
            }
        } else if (down == Integer.MIN_VALUE && up == Integer.MAX_VALUE) {
            for (int i = 0; i < size; i++) {
                array[i] = rnd.nextInt(); // Генерирует любое int
            }
        } else {
            for (int i = 0; i < size; i++) {
                double d = rnd.nextDouble();
                array[i] = (int) (down + d * (up - down + 1));
            }
        }

        return array;
    }

    private static int generateRandomInRange(Random rnd, long down, long up) {
        if (down > up) {
            long temp = down;
            down = up;
            up = temp;
        }

        down = Math.max(down, Integer.MIN_VALUE);
        up = Math.min(up, Integer.MAX_VALUE);

        if (down == up) {
            return (int) down;
        } else if (down == Integer.MIN_VALUE && up == Integer.MAX_VALUE) {
            return rnd.nextInt(); // Генерирует любое int
        } else {
            double d = rnd.nextDouble();
            return (int) (down + d * (up - down + 1));
        }
    }
}