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

    private Word answer;
    private Button lastPinyinButton;
    private Button lastMeaningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Random random = new Random();
        Button option1 = (Button) findViewById(R.id.option1);
        Button option2 = (Button) findViewById(R.id.option2);
        Button option3 = (Button) findViewById(R.id.option3);
        Button pinyinButton1 = (Button) findViewById(R.id.pinyinoption1);
        Button pinyinButton2 = (Button) findViewById(R.id.pinyinoption2);
        Button pinyinButton3 = (Button) findViewById(R.id.pinyinoption3);
        Intent intent = getIntent();
        List<Word> randomWords = (List<Word>) intent.getSerializableExtra("RANDOMWORDS");
        int wordUsedInQuestion = random.nextInt(randomWords.size());
        answer = randomWords.get(wordUsedInQuestion);
        Button toBeTranslated = (Button) findViewById(R.id.toBeTranslated);
        toBeTranslated.setText(answer.getLabel());

        option1.setText(randomWords.get(0).getMeaning());
        option2.setText(randomWords.get(1).getMeaning());
        option3.setText(randomWords.get(2).getMeaning());
        pinyinButton1.setText(randomWords.get(0).getPinyin());
        pinyinButton3.setText(randomWords.get(2).getPinyin());
        pinyinButton2.setText(randomWords.get(1).getPinyin());
    }

    public void onSelectingPinyinOption(View v) {
        resetAllPinyinButtons();
        Button clickedButton = (Button) v;
        clickedButton.setEnabled(false);
        lastPinyinButton = clickedButton;
        if ((lastPinyinButton != null) && (lastMeaningButton != null) && !lastPinyinButton.isEnabled() && !lastMeaningButton.isEnabled()) {
            checkSolutions();
        }
    }

    public void onSelectingMeaningOption(View v) {
        resetAllMeaningButtons();
        Button clickedButton = (Button) v;
        clickedButton.setEnabled(false);
        lastMeaningButton = clickedButton;
        if ((lastPinyinButton != null) && (lastMeaningButton != null)&&!lastPinyinButton.isEnabled() && !lastMeaningButton.isEnabled()) {
            checkSolutions();
        }
    }

    private void resetAllPinyinButtons() {
        Button pinyinButton1 = (Button) findViewById(R.id.pinyinoption1);
        pinyinButton1.setEnabled(true);
        Button pinyinButton2 = (Button) findViewById(R.id.pinyinoption2);
        pinyinButton2.setEnabled(true);
        Button pinyinButton3 = (Button) findViewById(R.id.pinyinoption3);
        pinyinButton3.setEnabled(true);
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
        if (answer.getMeaning().equals(lastMeaningButton.getText()) && answer.getPinyin().equals(lastPinyinButton.getText())) {
            lastPinyinButton.setEnabled(true);
            lastMeaningButton.setEnabled(true);
            Intent intent = new Intent(this, Home.class);
            WordsGenerator wordsGenerator = new WordsGenerator(getApplicationContext());
            List randomWords = wordsGenerator.getRandomWords(3);
            intent.putExtra("RANDOMWORDS", (Serializable) randomWords);
            startActivity(intent);
            startActivity(intent);
        } else {
            lastPinyinButton.setEnabled(true);
            lastMeaningButton.setEnabled(true);
        }
    }
}
