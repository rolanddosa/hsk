package com.knowYourHSK.hsk1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.knowYourHSK.R;

import java.util.List;
import java.util.Random;

public class TeacherActivity extends AppCompatActivity {

    Button toBeTranslated;
    WordsGenerator wordsGenerator;
    private Word answer;
    EditText solutionField;
    int streakCount;
    int highestStreak;
    TextView streakTextView;
    TextView highestStreakTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        wordsGenerator = new WordsGenerator(getApplicationContext());
        solutionField = (EditText) findViewById(R.id.solutionField);
        Random random = new Random();
        Intent intent = getIntent();
        List<Word> randomWords = (List<Word>) intent.getSerializableExtra("RANDOMWORDS");
        answer = randomWords.get(0);
        toBeTranslated = (Button) findViewById(R.id.toBeTranslated);
        toBeTranslated.setText(answer.getMeaning());
        if(savedInstanceState != null) {
            updateStreakTextView(savedInstanceState.getInt("streakCount"));
        }
        streakTextView = (TextView) findViewById(R.id.teacherStreak);
        streakTextView.setText("Streak: " + streakCount);
        highestStreakTextView = (TextView) findViewById(R.id.highestTeacherStreak);
        highestStreakTextView.setText("Highest: " + highestStreak);
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        savedInstanceState.putInt("streakCount", streakCount);
//        // etc.
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        updateStreakTextView(savedInstanceState.getInt("streakCount"));
//        Toast.makeText(TeacherActivity.this,"RESTORED STATE",Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferencesTeacherActivity", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highestStreak", highestStreak);
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        getIntent().getExtras();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferencesTeacherActivity", 0);
        highestStreak = sharedPreferences.getInt("highestStreak", 0);
        highestStreakTextView = (TextView) findViewById(R.id.highestTeacherStreak);
        highestStreakTextView.setText("Streak: " + highestStreak);
    }

    public void checkSolution(View v) {
        if (answer.getLabel().equals(solutionField.getText().toString())) {
            updateStreakTextView(++streakCount);
        } else {
            updateStreakTextView(0);
        }
        nextWord();
    }

    private void nextWord() {
        solutionField.setText("");
        answer = (Word) wordsGenerator.getRandomWords(1).get(0);
        toBeTranslated.setText(answer.getMeaning());
    }

    private void updateStreakTextView(int count) {
        streakCount = count;
        if (count > highestStreak) {
            highestStreak = count;
        }
        streakTextView = (TextView) findViewById(R.id.teacherStreak);
        streakTextView.setText("Streak: " + streakCount);
        highestStreakTextView = (TextView) findViewById(R.id.highestTeacherStreak);
        highestStreakTextView.setText("Highest: " + highestStreak);
    }
}
