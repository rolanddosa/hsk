package com.knowYourHSK.hsk1;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


@RunWith(AndroidJUnit4.class)
public class WordsGeneratorTest {
    @Test
    public void testGetRandomWords() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        WordsGenerator wordsGenerator = new WordsGenerator(appContext);
        List listOfWords = wordsGenerator.getRandomWords(10);
        HashSet<Object> set = new HashSet<Object>(listOfWords);
        assertEquals(listOfWords.size(), set.size());
    }
}
