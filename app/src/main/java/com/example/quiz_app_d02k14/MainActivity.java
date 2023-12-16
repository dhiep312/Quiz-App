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

    public void btn_main(View view) {
        switch (view.getId()){
//            case R.id.btn_play:
//                startActivity(new Intent(MainActivity.this , PlayActivity.class));
//                break;
//            case R.id.btn_leaderBoard:
//                break;
//            case R.id.btn_setting:
//                break;
//            case R.id.btn_exit:
//                break;
        }
    }
}