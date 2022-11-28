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

    private RecyclerView recyclerView;
    private ArrayList<History> histories;
    private ImageView backNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // mapping
        backNavigation = findViewById(R.id.backNavigation);
        recyclerView = findViewById(R.id.RecycleViewHistory);

        // init data
        histories = new ArrayList<>();

        // call api
        initHistory();

        // handle click
        handleClick();
    }

    private void initHistory() {
        render(recyclerView, histories);
    }

    private void handleClick() {
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void render(RecyclerView recyclerView, ArrayList<History> data) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        RecycleHistoryAdapter adapter = new RecycleHistoryAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}