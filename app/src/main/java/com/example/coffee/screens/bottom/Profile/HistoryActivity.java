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
import com.example.coffee.callbacks.HistoryCallback;
import com.example.coffee.models.User.History;
import com.example.coffee.models.User.HistoryResponse;
import com.example.coffee.models.User.User;
import com.example.coffee.screens.bottom.MainActivity;
import com.example.coffee.services.HistoryService;
import com.example.coffee.utils.Logger;
import com.example.coffee.utils.UserInformation;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<History> histories;
    private ImageView backNavigation;
    private HistoryService historyService;

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

        // init service
        historyService = new HistoryService();

        // call api
        initHistory();

        // handle click
        handleClick();
    }

    private void initHistory() {
        User user = UserInformation.getUser(HistoryActivity.this);
        int id = user.getId();

        historyService.getAllHistoryOfUser(id, new HistoryCallback() {
            @Override
            public void onSuccess(Boolean value, HistoryResponse historyResponse) {
                Logger.log("RESPONSE", historyResponse);

                histories.addAll(historyResponse.getHistories());
                render(recyclerView, histories);
            }

            @Override
            public void onFailed(Boolean value) {
                Logger.log("RESPONSE", "ERROR");
            }
        });
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