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
import com.example.coffee.adapters.RecycleViewHistoryAdapter;
import com.example.coffee.models.User.History;
import com.example.coffee.screens.bottom.MainActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewHistoryAdapter adapter;
    private ArrayList<History> list = new ArrayList<>();

    ImageView backNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // mapping
        backNavigation = findViewById(R.id.backNavigation);

        recyclerView = findViewById(R.id.RecycleViewHistory);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewHistoryAdapter(this, list);
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        list.add(new History("22 August 2022, 10:00 AM", "Congratulation, you have successfully made a coffee purchase", "Aurora Coffee", "-50.000VND"));
        recyclerView.setAdapter(adapter);

        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}