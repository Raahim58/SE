package com.example.emotions;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emotions.DateUtils;
import com.example.emotions.LogEntry;
import com.example.emotions.LogRepository;
import com.example.emotions.LogsAdapter;

import java.time.LocalDate;
import java.util.List;

/*
  logs screen (emotion events screen).
  can browse day by day using prev/next.
*/
public class LogsActivity extends AppCompatActivity {
    private LogRepository repo;
    private LogsAdapter adapter;
    private LocalDate selectedDate;
    private TextView txtDate;
    private TextView txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        // setup toolbar as action bar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // enable back arrow
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        repo = LogRepository.getInstance(); // get logs from repository
        selectedDate = LocalDate.now();
        txtDate = findViewById(R.id.txtLogsDate);
        txtCount = findViewById(R.id.txtLogsCount);
        Button btnPrev = findViewById(R.id.btnLogsPrev);
        Button btnNext = findViewById(R.id.btnLogsNext);

        // recycler view for scrollable list of logs
        RecyclerView recycler = findViewById(R.id.recyclerLogs);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LogsAdapter(); // tells what to display for each log
        recycler.setAdapter(adapter);

        // navigate different dates
        btnPrev.setOnClickListener(v -> {
            selectedDate = selectedDate.minusDays(1);
            refresh();
        });
        btnNext.setOnClickListener(v -> {
            selectedDate = selectedDate.plusDays(1);
            refresh();
        });
        refresh();
    }

    private void refresh() {
        txtDate.setText("date: " + DateUtils.formatDate(selectedDate));
        List<LogEntry> logsForDay = repo.getForDay(selectedDate);
        txtCount.setText("total: " + logsForDay.size());
        adapter.setItems(logsForDay); // clear old data, add new data, recyclerview refreshed
    }

    @Override
    public boolean onSupportNavigateUp() {
        // same as pressing the system back button
        finish();
        return true;
    }
}


