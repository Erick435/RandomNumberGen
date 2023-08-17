package com.erick.dicegame;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText minEditText;
    private EditText maxEditText;
    private Button generateButton;
    private TextView randomNumberTextView;
    private EditText guessEditText;
    private TextView winTextView;
    private TextView lossTextView;
    private TextView totalTextView;
    private Button resetButton;

    private int wins = 0;
    private int losses = 0;
    private int totalAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minEditText = findViewById(R.id.minEditText);
        maxEditText = findViewById(R.id.maxEditText);
        generateButton = findViewById(R.id.generateButton);
        randomNumberTextView = findViewById(R.id.randomNumberTextView);
        guessEditText = findViewById(R.id.guessEditText);
        winTextView = findViewById(R.id.winTextView);
        lossTextView = findViewById(R.id.lossTextView);
        totalTextView = findViewById(R.id.totalTextView);
        resetButton = findViewById(R.id.resetButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScores();
            }
        });
    }

    private void generateRandomNumber() {
        String minText = minEditText.getText().toString().trim();
        String maxText = maxEditText.getText().toString().trim();

        if (minText.isEmpty() || maxText.isEmpty()) {
            // Handle case where min or max is empty
            return;
        }

        int min = Integer.parseInt(minText);
        int max = Integer.parseInt(maxText);

        if (min <= max) {
            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumberTextView.setText(String.valueOf(randomNumber));

            String guessText = guessEditText.getText().toString().trim();
            if (!guessText.isEmpty()) {
                int userGuess = Integer.parseInt(guessText);
                totalAttempts++;

                if (userGuess == randomNumber) {
                    wins++;
                } else {
                    losses++;
                }

                updateScores();
            }
        }
    }


    private void updateScores() {
        winTextView.setText("Win: " + wins);
        lossTextView.setText("Losses: " + losses);
        totalTextView.setText("Total: " + totalAttempts);
    }

    private void resetScores() {
        wins = 0;
        losses = 0;
        totalAttempts = 0;

        updateScores();
    }
}