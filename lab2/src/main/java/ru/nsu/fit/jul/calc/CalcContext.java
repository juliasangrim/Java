package ru.nsu.fit.jul.calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;



public class CalcContext {
    private Deque<Double> stack = new ArrayDeque<>();
    private Map<String, Double> params = new HashMap<>();

    public Deque<Double> getStack()
    {
        return stack;
    }

    public Map<String, Double> getParams()
    {
        return params;
    }

    public void updateStack(Deque<Double> newStack) {
        stack = newStack;
    }
}
