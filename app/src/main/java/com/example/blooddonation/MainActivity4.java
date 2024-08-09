package com.example.blooddonation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private Spinner bloodGroupSpinner;
    private EditText cityEditText;
    private RadioGroup searchTypeRadioGroup;
    private Button searchButton;
    private RecyclerView recyclerView;
    private DonorAdapter donorAdapter;
    private List<Donor> donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Initialize UI elements
        bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
        cityEditText = findViewById(R.id.cityEditText);
        searchTypeRadioGroup = findViewById(R.id.searchTypeRadioGroup);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Setup blood group spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupSpinner.setAdapter(adapter);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        donorList = new ArrayList<>();
        // Sample data for testing
        donorList.add(new Donor("Alice", "A+", "New York"));
        donorList.add(new Donor("Bob", "B+", "Los Angeles"));
        donorAdapter = new DonorAdapter(donorList);
        recyclerView.setAdapter(donorAdapter);

        // Search button logic
        searchButton.setOnClickListener(v -> {
            String selectedBloodGroup = bloodGroupSpinner.getSelectedItem().toString();
            String city = cityEditText.getText().toString().trim();
            int selectedSearchTypeId = searchTypeRadioGroup.getCheckedRadioButtonId();

            if (selectedBloodGroup.isEmpty()) {
                Toast.makeText(MainActivity4.this, "Please select a blood group", Toast.LENGTH_SHORT).show();
                return;
            }

            if (city.isEmpty()) {
                Toast.makeText(MainActivity4.this, "Please enter a city", Toast.LENGTH_SHORT).show();
                return;
            }

            String searchType = selectedSearchTypeId == R.id.donorRadioButton ? "Donor" : "Blood Request";
            performSearch(selectedBloodGroup, city, searchType);
        });

        // Back button functionality
        findViewById(R.id.imageViewBack).setOnClickListener(v -> finish());
    }

    // Method to filter donors based on search criteria
    private void performSearch(String bloodGroup, String city, String searchType) {
        List<Donor> filteredList = new ArrayList<>();
        for (Donor donor : donorList) {
            if (donor.getBloodGroup().equalsIgnoreCase(bloodGroup) && donor.getCity().equalsIgnoreCase(city)) {
                filteredList.add(donor);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        } else {
            donorAdapter.updateList(filteredList);
        }
    }
}
