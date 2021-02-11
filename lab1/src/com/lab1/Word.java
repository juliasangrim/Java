package com.lab1;

import java.util.List;

public class Word {
    private String _word;
    private int _freq;

    public Word(String word, int i) {
        this._word = word;
        this._freq = i;
    }

    public String get_word() {
        return _word;
    }
    public int get_freq() {
        return _freq;
    }
    public void change_freq() {
        _freq++;
    }
    public static int count(List<Word> words) {
        int count = 0;
        for (Word element : words) {
            count += element.get_freq();
        }
        return count;
    }
}
