package com.example.quiz_app_d02k14;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    // Update file names without extension in the "res/raw" folder
    private String questionFileName = "questions";
    private String userFileName = "users";
    private int number_of_questions = 0;
    private int current_question = 0;
    private String current_answer = "";
    private String correct_answer = "";
    private int score = 0;
    private String player_name = "";
    private String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("player_name")) {
            this.player_name = intent.getStringExtra("player_name");
            this.mode = intent.getStringExtra("mode");
            assert this.mode != null;
            game_init(this.mode);
        }
        init_question();



    }

    public void game_init(String game_mode) {

        switch (game_mode) {
            case "Easy":
                this.number_of_questions = 10;
                break;
            case "Normal":
                this.number_of_questions = 20;
                break;
            case "Hard":
                this.number_of_questions = 30;
                break;
        }
        Log.d("Mode", game_mode);
    }

    public void init_question() {
        try {
            InputStream inputStream = getResources().openRawResource(
                    getResources().getIdentifier(questionFileName, "raw", getPackageName()));

            // Read CSV file using CSVReader
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            List<String> record = extractRandomRow(csvReader);
            String question_text = record.get(1);
            String a_btn_text = record.get(2);
            String b_btn_text = record.get(3);
            String c_btn_text = record.get(4);
            this.correct_answer = record.get(5) + "\"";
            TextView question_field = findViewById(R.id.text_question);
            Button a_btn = findViewById(R.id.btn_ans_1);
            Button b_btn = findViewById(R.id.btn_ans_2);
            Button c_btn = findViewById(R.id.btn_ans_3);
            question_field.setText(question_text);
            a_btn.setText(a_btn_text);
            b_btn.setText(b_btn_text);
            c_btn.setText(c_btn_text);
            this.current_question += 1;
            Log.d("current question", String.valueOf(this.current_question));
            Log.d("Max question", String.valueOf(this.number_of_questions));
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> extractRandomRow(CSVReader csvReader) throws CsvValidationException {
        try {
            List<List<String>> allRecords = readCsvFile(csvReader);
            List<String> randomRecord = RandomRecord(allRecords);
            return randomRecord;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<String>> readCsvFile(CSVReader csvReader) throws IOException, CsvValidationException {
        List<List<String>> records = new ArrayList<>();
        String[] record;

        while ((record = csvReader.readNext()) != null) {
            List<String> rowData = new ArrayList<>();

            // Assuming the first field contains a list of elements in square brackets
            String firstField = record[0];

            // Remove square brackets and split the string into a list
            List<String> innerList = Arrays.asList(firstField.replaceAll("\\[|\\]", "").split(";"));

            // Add the elements of the inner list to rowData
            rowData.addAll(innerList);

            // Add the remaining fields to rowData
            for (int i = 1; i < record.length; i++) {
                rowData.add(record[i]);
            }
            records.add(rowData);
        }
        return records;
    }
    public List<String> RandomRecord(List<List<String>> records) {
        if (records == null || records.isEmpty()) {
            return null; // Handle the case where records are empty or null
        }

        Random random = new Random();
        int randomIndex = random.nextInt(records.size());

        return records.get(randomIndex);
    }

    public void load_next_question(View view) {
        String current_answer = this.current_answer.replace("\"", "");
        String correct_answer = this.correct_answer.replace("\"", "");
        if (current_answer.equals(correct_answer)) {
            this.score++;
        }
        Log.d("current",current_answer);
        Log.d("actual",correct_answer);
        Log.d("Score", String.valueOf(this.score));
        if (current_question > number_of_questions) {
            Log.d("Moveeedcvsdvsvse","Moving on to next activity");
            Intent intent = new Intent(PlayActivity.this, WinLose.class);
            intent.putExtra("player_name",this.player_name);
            intent.putExtra("mode",this.mode);
            String total_score = String.valueOf(this.score);
            String total_ques = String.valueOf(this.number_of_questions);
            intent.putExtra("score",total_score);
            intent.putExtra("total_ques",total_ques);
            startActivity(intent);


        }else {
            init_question();
            resetButtonColors();
        }

    }
    private void resetButtonColors() {
        // Set default color for all buttons
        Button a_btn = findViewById(R.id.btn_ans_1);
        Button b_btn = findViewById(R.id.btn_ans_2);
        Button c_btn = findViewById(R.id.btn_ans_3);
        a_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
        b_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
        c_btn.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color));
    }

    public void btn_a_clicked(View v){
        Button btn_a = findViewById(R.id.btn_ans_1);
        this.current_answer = (String) btn_a.getText();
        resetButtonColors();
        // Example using the hexadecimal representation of green
        btn_a.setBackgroundColor(Color.parseColor("#004000"));

        Log.d("Answer",this.current_answer);
        Log.d("Real answer",this.correct_answer);
    }
    public void btn_b_clicked(View v){
        Button btn_b = findViewById(R.id.btn_ans_2);
        this.current_answer = (String) btn_b.getText();
        resetButtonColors();
        // Example using the hexadecimal representation of green
        btn_b.setBackgroundColor(Color.parseColor("#004000"));

        Log.d("Answer",this.current_answer);
        Log.d("Real answer",this.correct_answer);
    }
    public void btn_c_clicked(View v){
        Button btn_c = findViewById(R.id.btn_ans_3);
        this.current_answer = (String) btn_c.getText();
        resetButtonColors();
        // Example using the hexadecimal representation of green
        btn_c.setBackgroundColor(Color.parseColor("#004000"));


        Log.d("Answer",this.current_answer);
        Log.d("Real answer",this.correct_answer);
    }
}
