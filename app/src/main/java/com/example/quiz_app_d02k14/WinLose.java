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
        this.player_name = intent.getStringExtra("player_name");
        this.mode = intent.getStringExtra("mode");
        this.score = intent.getStringExtra("score");
        String total_questions = intent.getStringExtra("total_ques");

        TextView txt_view = findViewById(R.id.txt_name);
        TextView score_view = findViewById(R.id.txt_score2);
        TextView dif_view = findViewById(R.id.txt_difficulty2);
        TextView total_view = findViewById(R.id.txt_total2);

        txt_view.setText(this.player_name);
        score_view.setText(this.score);
        dif_view.setText(this.mode);
        total_view.setText(this.score + "/" +total_questions);
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