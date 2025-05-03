package com.example;

import java.util.*;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.function.Supplier;

public class TestCases {

    public static List<TestCase> generateAllTestCases() {
        List<TestCase> allTestCases = new ArrayList<>();
        int step = 3;

        Map<String, Supplier<TestCase>> testCaseSuppliers = new LinkedHashMap<>() {{
            put("StreamGraphPath1", () -> TestGenerator.generateTestCase("StreamGraphPath1", 0, true, 0, 0, false));
            put("StreamGraphPath2", () -> TestGenerator.generateTestCase("StreamGraphPath2", 0, false, 0, 0, false));
            put("StreamGraphPath3", () -> TestGenerator.generateTestCase("StreamGraphPath3", 1025, false, MIN_VALUE, MAX_VALUE, true));
            put("StreamGraphPath4", () -> TestGenerator.generateTestCase("StreamGraphPath4", 2, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath5", () -> TestGenerator.generateTestCase("StreamGraphPath5", 2, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath6", () -> TestGenerator.generateTestCase("StreamGraphPath6", 1 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath7", () -> TestGenerator.generateTestCase("StreamGraphPath7", 2 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath8", () -> TestGenerator.generateTestCase("StreamGraphPath8", 3 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath9", () -> TestGenerator.generateTestCase("StreamGraphPath9", 4 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath10", () -> TestGenerator.generateTestCase("StreamGraphPath10", 5 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath11", () -> TestGenerator.generateTestCase("StreamGraphPath11", 6 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath12", () -> TestGenerator.generateTestCase("StreamGraphPath12", 7 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath13", () -> TestGenerator.generateTestCase("StreamGraphPath13", 8 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath14", () -> TestGenerator.generateTestCase("StreamGraphPath14", 9 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("StreamGraphPath15", () -> TestGenerator.generateTestCase("StreamGraphPath15", 10 + step, false, MIN_VALUE, MAX_VALUE, true));

            // Info graph tests
            put("InfoGraphPath1", () -> TestGenerator.generateTestCase("InfoGraphPath1", 8 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("InfoGraphPath2", () -> TestGenerator.generateTestCase("InfoGraphPath2", 9 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("InfoGraphPath3", () -> TestGenerator.generateTestCase("InfoGraphPath3", 10 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("InfoGraphPath4", () -> TestGenerator.generateTestCase("InfoGraphPath4", 11 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("InfoGraphPath5", () -> TestGenerator.generateTestCase("InfoGraphPath5", 12 + step, false, MIN_VALUE, MAX_VALUE, false));

            // Equivalence class tests
            put("EquivalenceClass1", () -> TestGenerator.generateTestCase("EquivalenceClass1", 0, true, 0, 0, false));
            put("EquivalenceClass2", () -> TestGenerator.generateTestCase("EquivalenceClass2", 1 + 11 * step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass3", () -> TestGenerator.generateTestCase("EquivalenceClass3", 1, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass4", () -> TestGenerator.generateTestCase("EquivalenceClass4", 1023, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass5", () -> TestGenerator.generateTestCase("EquivalenceClass5", 1024, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass6", () -> TestGenerator.generateTestCase("EquivalenceClass6", 1025, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass7", () -> TestGenerator.generateTestCase("EquivalenceClass7", 13 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass8", () -> TestGenerator.generateTestCase("EquivalenceClass8", 14 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass9", () -> TestGenerator.generateTestCase("EquivalenceClass9", 15 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass10", () -> TestGenerator.generateTestCase("EquivalenceClass10", 16 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass11", () -> TestGenerator.generateTestCase("EquivalenceClass11", 17 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass12", () -> TestGenerator.generateTestCase("EquivalenceClass12", 18 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass13", () -> TestGenerator.generateTestCase("EquivalenceClass13", 19 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass14", () -> TestGenerator.generateTestCase("EquivalenceClass14", 20 + step, false, MIN_VALUE, MAX_VALUE, false));
            put("EquivalenceClass15", () -> TestGenerator.generateTestCase("EquivalenceClass15", 21 + step, false, MIN_VALUE, MAX_VALUE, true));
            put("EquivalenceClass16", () -> TestGenerator.generateTestCase("EquivalenceClass16", 22 + step, false, MIN_VALUE, MAX_VALUE, true));
            put("EquivalenceClass17", () -> TestGenerator.generateTestCase("EquivalenceClass17", 23 + step, false, MIN_VALUE, MAX_VALUE, true));
            put("EquivalenceClass18", () -> TestGenerator.generateTestCase("EquivalenceClass18", 24 + step, false, MIN_VALUE, MAX_VALUE, true));
        }};

        testCaseSuppliers.values().forEach(supplier -> allTestCases.add(supplier.get()));
        return allTestCases;
    }
}

