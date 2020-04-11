package com.knowYourHSK.hsk1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.knowYourHSK.R;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity {

    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Random random = new Random();
        Button toBeTranslated = (Button) findViewById(R.id.toBeTranslated);
        Button option1 = (Button) findViewById(R.id.option1);
        Button option2 = (Button) findViewById(R.id.option2);
        Button option3 = (Button) findViewById(R.id.option3);
        Intent intent = getIntent();
        List<Word> randomWords = (List<Word>) intent.getSerializableExtra("RANDOMWORDS");
        int wordUsedInQuestion = random.nextInt(randomWords.size());
        correctAnswer = randomWords.get(wordUsedInQuestion).getMeaning();
        toBeTranslated.setText(randomWords.get(wordUsedInQuestion).getLabel());

        option1.setText(randomWords.get(0).getMeaning());
        option2.setText(randomWords.get(1).getMeaning());
        option3.setText(randomWords.get(2).getMeaning());
    }

    public void onTryOption(View v) {
        Button toBeTranslatedButton = (Button) findViewById(R.id.toBeTranslated);
        Button clickedButton = (Button) v;
        if (correctAnswer.equals(clickedButton.getText())) {
            Intent intent = new Intent(this, Home.class);

            WordsGenerator wordsGenerator = new WordsGenerator(getApplicationContext());
            List randomWords = wordsGenerator.getRandomWords(3);

            intent.putExtra("RANDOMWORDS", (Serializable) randomWords);
            startActivity(intent);
            startActivity(intent);
        }
    }
}
