package com.example.quiz_app_d02k14;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStart {

    private String question_file_path ="D:/Android/Quiz-App/app/src/main/res/raw/questions.csv";
    private String user_file ="D:/Android/Quiz-App/app/src/main/res/raw/users.csv";

    public GameStart() {

    }

    public List<List<String>> readCsvFile() throws IOException, CsvValidationException {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(question_file_path))
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

//    public static void main(String[] args) throws CsvValidationException {
//        extractRandomRow();
//    }

    public static List<String> extractRandomRow() throws CsvValidationException{
        try {
            GameStart csvFileProcessor = new GameStart();

            List<List<String>> allRecords = csvFileProcessor.readCsvFile();

            // Display all records
//            System.out.println("All Records:");
//            for (List<String> record : allRecords) {
//                System.out.println(record);
//            }

            // Extract and display a random record
            List<String> randomRecord = csvFileProcessor.extractRandomRecord(allRecords);
            return randomRecord;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

