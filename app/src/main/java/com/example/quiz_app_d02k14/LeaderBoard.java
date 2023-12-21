package com.example.quiz_app_d02k14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

public class LeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
    }
    public void backToHome(View v) {
//        Button back_btn = findViewById(R.id.btn_Back);
        Intent intent = new Intent(LeaderBoard.this, MainActivity.class);
        startActivity(intent);

    }
}