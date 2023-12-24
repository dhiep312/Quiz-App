package com.example.quiz_app_d02k14;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {
    private String user_file_name = "users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);


        try {
            InputStream inputStream = getResources().openRawResource(getResources().getIdentifier(user_file_name, "raw", getPackageName()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            List<List<String>> allRecords = readCsvFile(csvReader);


            Log.d("Extract user info","Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public void backToHome(View v) {
//        Button back_btn = findViewById(R.id.btn_Back);
        Intent intent = new Intent(LeaderBoard.this, MainActivity.class);
        startActivity(intent);

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
//    public List<String> extractRandomRow(CSVReader csvReader) throws CsvValidationException {
//        try {
//            List<List<String>> allRecords = readCsvFile(csvReader);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}