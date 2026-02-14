package com.example.emotions;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

/*
  summary screen.
  now it's daily: you can browse day by day using prev/next.
  it shows counts + percentages for the selected day.
*/
public class SummaryActivity extends AppCompatActivity {

    private LogRepository repo;
    private SummaryAdapter adapter;

    private LocalDate selectedDate;

    private TextView txtDate;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // show back arrow in the top bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        repo = LogRepository.getInstance();
        selectedDate = LocalDate.now();

        txtDate = findViewById(R.id.txtSummaryDate);
        txtTotal = findViewById(R.id.txtSummaryTotal);

        Button btnPrev = findViewById(R.id.btnSummaryPrev);
        Button btnNext = findViewById(R.id.btnSummaryNext);

        RecyclerView recycler = findViewById(R.id.recyclerSummary);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SummaryAdapter();
        recycler.setAdapter(adapter);

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
        txtTotal.setText("total: " + logsForDay.size());

        List<SummaryRow> rows = SummaryCalculator.compute(logsForDay);
        adapter.setItems(rows);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
