package com.example.quiz_app_d02k14;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private String question_file_path ="D:/Android/Quiz-App/app/src/main/res/raw/questions.csv";
    private String user_file ="D:/Android/Quiz-App/app/src/main/res/raw/users.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("player_name")) {
            String player_name = intent.getStringExtra("player_name");
            String selected_mode = intent.getStringExtra("mode");
            game_init(selected_mode);
            init();

        }
    }
    public void init(){
        try {
            final List<String> record = extractRandomRow();
            String firstField = record.get(0);
            String secondField = record.get(1);
            Button a_btn = findViewById(R.id.btn_ans_3);
            a_btn.setText(secondField);

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public void game_init(String game_mode){
        int number_of_questions ;
        switch (game_mode){
            case "Easy":
                number_of_questions = 10;
                break;
            case "Normal":
                number_of_questions = 20;
                break;
            case "Hard":
                number_of_questions = 30;
                break;
        }
        Log.d("Mode",game_mode);
    }

    public List<List<String>> readCsvFile() throws IOException, CsvValidationException {

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(this.question_file_path))
                .withSkipLines(1) // Skip header line if present
                .build()) {

            List<List<String>> records = new ArrayList<>();
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                List<String> rowData = new ArrayList<>();
                for (String field : record) {
                    rowData.add(field);
                }
                records.add(rowData);
            }
            Log.d("Read file successfully","vhbsfhgdjryhgdbv");
            return records;
        }
    }

    public List<String> extractRandomRecord(List<List<String>> records) {
        if (records == null || records.isEmpty()) {
            return null; // Handle the case where records are empty or null
        }

        Random random = new Random();
        int randomIndex = random.nextInt(records.size());

        return records.get(randomIndex);
    }
    public List<String> extractRandomRow() throws CsvValidationException{
        try {

            List<List<String>> allRecords = readCsvFile();
            List<String> randomRecord = extractRandomRecord(allRecords);
            return randomRecord;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}