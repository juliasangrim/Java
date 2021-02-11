package com.lab1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Reader implements iReader{
    public static void readerFile(String fileName, Map<String, Word> words) throws IOException {
        try (FileReader in = new FileReader(fileName)) {
            StringBuilder word = new StringBuilder();
            int c;
            while ((c = in.read()) != -1) {
                char cc = (char) c;
                if (Character.isLetterOrDigit(cc)) {
                    word.append(Character.toLowerCase(cc));
                }
                else if (word.length() > 0) {
                    String finalWord = word.toString();
                    words.compute(finalWord, (key, prev) -> {
                        if (prev == null) prev = new Word(finalWord, 1);
                        else  prev.change_freq();
                        return prev;
                    });
                    word.setLength(0);
                }
            }
            if (word.length() > 0) {
                String finalWord = word.toString();
                words.compute(finalWord, (key, prev) -> {
                    if (prev == null) prev = new Word(finalWord, 1);
                    else  prev.change_freq();
                    return prev;
                });
                word.setLength(0);
            }
        }
        catch (IOException e) {
          e.printStackTrace();
        }
    };
}
