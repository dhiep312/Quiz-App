package com.example.quiz_app_d02k14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinLose extends AppCompatActivity {
    String player_name = "";
    String mode = "";
    String score = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lose);
        Intent intent = getIntent();
        String player_name = intent.getStringExtra("player_name");
        String mode = intent.getStringExtra("mode");
        String score = intent.getStringExtra("score");
        String total_questions = intent.getStringExtra("total_ques");

        TextView txt_view = findViewById(R.id.txt_name);
        TextView score_view = findViewById(R.id.txt_score2);
        TextView dif_view = findViewById(R.id.txt_difficulty2);
        TextView total_view = findViewById(R.id.txt_total2);

        txt_view.setText(player_name);
        score_view.setText(score);
        dif_view.setText(mode);
        total_view.setText(score + "/" +total_questions);





    }

    public void play_again_btn_clicked(View view) {
        Intent intent = new Intent(WinLose.this, selectDifficulty.class);
        startActivity(intent);
    }

    public void menu_btn_clicked(View view) {
        Intent intent = new Intent(WinLose.this, MainActivity.class);
        startActivity(intent);
    }
}