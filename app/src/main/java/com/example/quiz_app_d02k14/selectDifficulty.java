package com.example.quiz_app_d02k14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class selectDifficulty extends AppCompatActivity {
    private String selectedMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);


    }
    public void backToHome(View v) {
//        Button back_btn = findViewById(R.id.btn_Back);
        Intent intent = new Intent(selectDifficulty.this, MainActivity.class);
        startActivity(intent);
    }

    public void game_on(View view) {
        // Get user input from the EditText
        TextView editText = findViewById(R.id.editName);
        String userInput = editText.getText().toString();


        // Example: Print the values to the console
        Log.d("Player Name", userInput);
        Log.d("Mode",this.selectedMode);
    }

    public void choose_mode(View view) { //onclick handler when mode buttons clicked
        // Get the selected game mode from the clicked button
        int id = view.getId();
        String viewName = getResources().getResourceEntryName(id);

        switch (viewName) {
            case "btn_easy":
                this.selectedMode = "Easy";
                break;
            case "btn_normal":
                this.selectedMode = "Easy";
                break;
            case "btn_hard":
                this.selectedMode = "Easy";
                break;

        }
    }
}