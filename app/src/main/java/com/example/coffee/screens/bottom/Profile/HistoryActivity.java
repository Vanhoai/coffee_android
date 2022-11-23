package com.example.coffee.screens.bottom.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coffee.R;
import com.example.coffee.adapters.RecycleHistoryAdapter;
import com.example.coffee.models.User.History;
import com.example.coffee.screens.bottom.MainActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<History> histories;
    ImageView backNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // mapping
        backNavigation = findViewById(R.id.backNavigation);
        recyclerView = findViewById(R.id.RecycleViewHistory);

        histories = new ArrayList<>();
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        histories.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        render(recyclerView, histories);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void render(RecyclerView recyclerView, ArrayList<History> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        RecycleHistoryAdapter adapter = new RecycleHistoryAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}