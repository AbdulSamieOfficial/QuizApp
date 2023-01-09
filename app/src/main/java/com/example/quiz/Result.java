package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView scoreText;
    TextView statusText;
    MediaPlayer soundAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        scoreText = findViewById(R.id.txtscore);
        statusText = findViewById(R.id.txtStatus);
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        scoreText.setText(scores);
        statusText.setText(showResult(scores));
        soundAlert.start();
    }

    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }

    private String showResult(String scores){
        int score = Integer.parseInt(scores);

        if(score >= 8){
            soundAlert = MediaPlayer.create(this, R.raw.high_score);
            return "Congratulations, You have got good marks.";
        }

        if (score >= 5){
            soundAlert = MediaPlayer.create(this,  R.raw.medium_score);
            return "You barely got passing marks...";
        }

        soundAlert = MediaPlayer.create(this,  R.raw.low_score);
        return "You have failed the quiz.";

    }
}
