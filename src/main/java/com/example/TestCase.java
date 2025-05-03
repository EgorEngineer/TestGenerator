package com.example;
import java.util.ArrayList;
import java.util.List;

public class TestCase {
    public String name;
    public int[] input;
    public int expectedIndex;
    public int expectedValue;
    public long expectedSum;
    public long expectedCube;
    public int[] expectedTransformedArray;
    public List<String> expectedErrors;

    public int actualIndex;
    public int actualValue;
    public long actualSum;
    public long actualCube;
    public int[] actualTransformedArray;
    public List<String> actualErrors;

    public TestCase() {
        expectedErrors = new ArrayList<>();
        actualErrors = new ArrayList<>();
    }
}
