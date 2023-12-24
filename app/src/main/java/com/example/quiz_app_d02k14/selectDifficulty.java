package com.example.quiz_app_d02k14;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

        Intent intent = new Intent(selectDifficulty.this, PlayActivity.class);
        // Example: Print the values to the console
//        Log.d("Player Name", userInput);
//        Log.d("Mode",this.selectedMode);
        intent.putExtra("player_name",userInput); //Pass info to another activity
        intent.putExtra("mode",this.selectedMode);
        startActivity(intent);
    }

    public void choose_mode(View view) { //onclick handler when mode buttons clicked
        // Get the selected game mode from the clicked button
        int id = view.getId();
        String viewName = getResources().getResourceEntryName(id);

        switch (viewName) {
            case "btn_easy":
                this.selectedMode = "Easy";
                resetButtonColors();
                Button ez_btn = findViewById(R.id.btn_easy);
                ez_btn.setBackgroundColor(Color.parseColor("#004000"));
                break;
            case "btn_normal":
                this.selectedMode = "Normal";
                resetButtonColors();
                Button nm_btn = findViewById(R.id.btn_normal);
                nm_btn.setBackgroundColor(Color.parseColor("#004000"));
                break;
            case "btn_hard":
                this.selectedMode = "Hard";
                resetButtonColors();
                Button h_btn = findViewById(R.id.btn_hard);
                h_btn.setBackgroundColor(Color.parseColor("#004000"));
                break;

        }
    }
    private void resetButtonColors() {
        // Set default color for all buttons
        Button a_btn = findViewById(R.id.btn_easy);
        Button b_btn = findViewById(R.id.btn_normal);
        Button c_btn = findViewById(R.id.btn_hard);
        a_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
        b_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
        c_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
    }
}