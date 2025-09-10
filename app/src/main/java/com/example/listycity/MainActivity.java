package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private EditText editCity;
    private Button btnAdd, btnDelete;

    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;

    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        editCity = findViewById(R.id.edit_city);
        btnAdd   = findViewById(R.id.btn_add);
        btnDelete= findViewById(R.id.btn_delete);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney",
                "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(
                this,
                R.layout.content,
                R.id.content_view,
                dataList
        );
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        // ADD CITY
        btnAdd.setOnClickListener(v -> {
            String name = editCity.getText().toString().trim();
            if (!name.isEmpty()) {
                dataList.add(name);
                cityAdapter.notifyDataSetChanged();
                editCity.setText("");
            }
        });

        // DELETE CITY
        btnDelete.setOnClickListener(v -> {
            if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }
}
