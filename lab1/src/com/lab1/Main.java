package com.lab1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Main {
    public  static void main(String[] args) {
        Map<String, Word> words = new HashMap<>();
        try {
            Reader.readerFile(args[0], words);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        var list = new ArrayList<>(words.values());
        Comparator<Word> freqComparator = new FreqComparator();
        list.sort(freqComparator);
        try {
            Writer.writerFile(list);
        }
        catch (IOException e){
           e.printStackTrace();
        }
    }
}
