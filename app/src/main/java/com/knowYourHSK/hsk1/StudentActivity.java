package com.knowYourHSK.hsk1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.knowYourHSK.R;

import java.util.List;
import java.util.Random;

public class StudentActivity extends AppCompatActivity {

    private Word answer;
    private Button selectedPinyinButton;
    private Button selectedMeaningButton;
    int streak;
    int highestStreak;
    WordsGenerator wordsGenerator;
    TextView streakTextView;
    TextView highestStreakTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        wordsGenerator = new WordsGenerator(getApplicationContext());
        Button option1 = (Button) findViewById(R.id.option1);
        Button option2 = (Button) findViewById(R.id.option2);
        Button option3 = (Button) findViewById(R.id.option3);
        Button pinyinButton1 = (Button) findViewById(R.id.pinyinoption1);
        Button pinyinButton2 = (Button) findViewById(R.id.pinyinoption2);
        Button pinyinButton3 = (Button) findViewById(R.id.pinyinoption3);
        Intent intent = getIntent();
        List<Word> randomWords = (List<Word>) intent.getSerializableExtra("RANDOMWORDS");
        Random random = new Random();
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

        streakTextView = (TextView) findViewById(R.id.studentStreak);
        streakTextView.setText("Streak: " + streak);
        highestStreakTextView = (TextView) findViewById(R.id.highestStudentStreak);
        highestStreakTextView.setText("Highest: " + highestStreak);
    }

    public void onSelectingPinyinOption(View v) {
        resetAllPinyinButtons();
        Button clickedButton = (Button) v;
        clickedButton.setEnabled(false);
        selectedPinyinButton = clickedButton;
        if ((selectedPinyinButton != null) && (selectedMeaningButton != null) && !selectedPinyinButton.isEnabled() && !selectedMeaningButton.isEnabled()) {
            checkSolutions();
        }
    }

    public void onSelectingMeaningOption(View v) {
        resetAllMeaningButtons();
        Button clickedButton = (Button) v;
        clickedButton.setEnabled(false);
        selectedMeaningButton = clickedButton;
        if ((selectedPinyinButton != null) && (selectedMeaningButton != null) && !selectedPinyinButton.isEnabled() && !selectedMeaningButton.isEnabled()) {
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

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferencesStudentActivity", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highestStreak", highestStreak);
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        getIntent().getExtras();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferencesStudentActivity", 0);
        highestStreak = sharedPreferences.getInt("highestStreak", 0);
//        highestStreak = 0;
        highestStreakTextView = (TextView) findViewById(R.id.highestStudentStreak);
        highestStreakTextView.setText("Highest: " + highestStreak);
    }

    public void checkSolutions() {
        if (answer.getMeaning().equals(selectedMeaningButton.getText()) && answer.getPinyin().equals(selectedPinyinButton.getText())) {
            selectedPinyinButton.setEnabled(true);
            selectedMeaningButton.setEnabled(true);
            updateStreakTextView(++streak);
        } else {
            selectedPinyinButton.setEnabled(true);
            selectedMeaningButton.setEnabled(true);
            updateStreakTextView(0);
        }
        nextWords();
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
        Button pinyinButton1 = (Button) findViewById(R.id.pinyinoption1);
        Button pinyinButton2 = (Button) findViewById(R.id.pinyinoption2);
        Button pinyinButton3 = (Button) findViewById(R.id.pinyinoption3);
        option1.setText(randomWords.get(0).getMeaning());
        option2.setText(randomWords.get(1).getMeaning());
        option3.setText(randomWords.get(2).getMeaning());
        pinyinButton1.setText(randomWords.get(0).getPinyin());
        pinyinButton3.setText(randomWords.get(2).getPinyin());
        pinyinButton2.setText(randomWords.get(1).getPinyin());
    }

    private void updateStreakTextView(int count) {
        streak = count;
        if (count > highestStreak) {
            highestStreak = count;
        }
        streakTextView = (TextView) findViewById(R.id.studentStreak);
        streakTextView.setText("Streak: " + streak);
        highestStreakTextView = (TextView) findViewById(R.id.highestStudentStreak);
        highestStreakTextView.setText("Highest: " + highestStreak);
    }
}
