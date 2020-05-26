package com.knowYourHSK.hsk1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.knowYourHSK.R;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream = getResources().openRawResource(R.raw.csvutf8);
        CSVFile csvFile = new CSVFile(inputStream);
        List words = csvFile.read();
    }

    public void newStudyActivity(View view) {
        Intent intent = new Intent(this, StudyActivity.class);
        startActivity(intent);
    }

    public void newBabyActivity(View view) {
        Intent intent = new Intent(this, BabyActivity.class);

        WordsGenerator wordsGenerator = new WordsGenerator(getApplicationContext());
        List randomWords = wordsGenerator.getRandomWords(3);

        intent.putExtra("RANDOMWORDS", (Serializable) randomWords);
        startActivity(intent);
    }

    public void newStudentActivity(View view) {
        Intent intent = new Intent(this, StudentActivity.class);

        WordsGenerator wordsGenerator = new WordsGenerator(getApplicationContext());
        List randomWords = wordsGenerator.getRandomWords(3);

        intent.putExtra("RANDOMWORDS", (Serializable) randomWords);
        startActivity(intent);
    }

    public void newTeacherActivity(View view) {
        Intent intent = new Intent(this, TeacherActivity.class);

        WordsGenerator wordsGenerator = new WordsGenerator(getApplicationContext());
        List<Word> randomWords = wordsGenerator.getRandomWords(1);

        intent.putExtra("RANDOMWORDS", (Serializable) randomWords);
        startActivity(intent);
    }
}
