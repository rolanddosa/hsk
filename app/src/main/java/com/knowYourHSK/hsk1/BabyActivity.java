package com.knowYourHSK.hsk1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.knowYourHSK.R;

import java.util.List;
import java.util.Random;

public class BabyActivity extends AppCompatActivity {

    private Word answer;
    private Button selectedMeaningButton;
    WordsGenerator wordsGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby);
        wordsGenerator = new WordsGenerator(getApplicationContext());
        Random random = new Random();
        Button option1 = (Button) findViewById(R.id.option1);
        Button option2 = (Button) findViewById(R.id.option2);
        Button option3 = (Button) findViewById(R.id.option3);
        Intent intent = getIntent();
        List<Word> randomWords = (List<Word>) intent.getSerializableExtra("RANDOMWORDS");
        int wordUsedInQuestion = random.nextInt(randomWords.size());
        answer = randomWords.get(wordUsedInQuestion);
        Button toBeTranslated = (Button) findViewById(R.id.toBeTranslated);
        toBeTranslated.setText(answer.getLabel());
        option1.setText(randomWords.get(0).getMeaning());
        option2.setText(randomWords.get(1).getMeaning());
        option3.setText(randomWords.get(2).getMeaning());
    }


    public void onSelectingMeaningOption(View v) {
        resetAllMeaningButtons();
        Button clickedButton = (Button) v;
        clickedButton.setEnabled(false);
        selectedMeaningButton = clickedButton;
        if (selectedMeaningButton != null && !selectedMeaningButton.isEnabled()) {
            checkSolutions();
        }
    }

    private void resetAllMeaningButtons() {
        Button option1 = (Button) findViewById(R.id.option1);
        option1.setEnabled(true);
        Button option2 = (Button) findViewById(R.id.option2);
        option2.setEnabled(true);
        Button option3 = (Button) findViewById(R.id.option3);
        option3.setEnabled(true);
    }

    public void checkSolutions() {
        if (answer.getMeaning().equals(selectedMeaningButton.getText())) {
            selectedMeaningButton.setEnabled(true);
            nextWords();
        } else {
            selectedMeaningButton.setEnabled(true);
        }
    }

    private void nextWords() {
        List<Word> randomWords = wordsGenerator.getRandomWords(3);
        Random random = new Random();
        int wordUsedInQuestion = random.nextInt(randomWords.size());
        answer = randomWords.get(wordUsedInQuestion);
        Button toBeTranslated = (Button) findViewById(R.id.toBeTranslated);
        toBeTranslated.setText(answer.getLabel());
        Button option1 = (Button) findViewById(R.id.option1);
        Button option2 = (Button) findViewById(R.id.option2);
        Button option3 = (Button) findViewById(R.id.option3);
        option1.setText(randomWords.get(0).getMeaning());
        option2.setText(randomWords.get(1).getMeaning());
        option3.setText(randomWords.get(2).getMeaning());
    }
}
