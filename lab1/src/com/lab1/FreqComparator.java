package com.lab1;

import java.util.Comparator;

public class FreqComparator implements Comparator<Word> {
    @Override
    public int compare(Word a, Word b) {
        return b.get_freq() - a.get_freq();
    }
}
