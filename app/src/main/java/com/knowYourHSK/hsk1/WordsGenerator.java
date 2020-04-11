package com.knowYourHSK.hsk1;

import android.content.Context;

import com.knowYourHSK.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsGenerator {
    private Context context;

    public WordsGenerator(Context context) {
        this.context = context;
    }

    public List<Word> getAllHSK1Words() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.csvutf8);
        CSVFile csvFile = new CSVFile(inputStream);
        return csvFile.read();
    }

    public List getRandomWords(int numberOfWords) {
        Random random = new Random();
        List<Word> hsk1Words = getAllHSK1Words();
        List randomWords = new ArrayList<Word>();
        for (int i = 0; i < numberOfWords; i++) {
            randomWords.add(hsk1Words.get(random.nextInt(hsk1Words.size())));
        }
        return randomWords;
    }
}
