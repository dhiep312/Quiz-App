package com.example.quiz_app_d02k14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showLdb(View v){
        Intent intent = new Intent(MainActivity.this, LeaderBoard.class);
        startActivity(intent);
    }
    public void game_start(View view) {
        Intent intent = new Intent(MainActivity.this, selectDifficulty.class);
        startActivity(intent);
    }
}