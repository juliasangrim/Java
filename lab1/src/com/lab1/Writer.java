package com.lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.lab1.Word.count;

public class Writer {
    public static void writerFile(List<Word> list) throws IOException{
        try (FileWriter out = new FileWriter("output.csv")) {
            int countWord = count(list);
            out.write("Word,Freq,Freq(%)\n");
            for (Word element : list) {
                float percent = (float)element.get_freq() / countWord * 100;
                out.write(element.get_word() + "," + element.get_freq() + "," + percent + "," + "\n");
            }
        }
    }
}
