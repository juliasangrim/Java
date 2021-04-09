package ru.nsu.fit.jul.calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;



public class CalcContext {
    private final Deque<Double> stack = new ArrayDeque<>();
    private final Map<String, Double> params = new HashMap<>();

    public Deque<Double> getStack()
    {
        return stack;
    }

    public Map<String, Double> getParams()
    {
        return params;
    }

}
