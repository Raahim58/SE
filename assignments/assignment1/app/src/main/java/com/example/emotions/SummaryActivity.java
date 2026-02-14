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
  summary screen; you can browse day by day using prev/next
  controls screen logic like which day selected, what logs to fetch
  it shows counts + percentages for the selected day, sends data to adapter for displaying
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
        // setup toolbar as action bar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // show back arrow in the top bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // get data
        repo = LogRepository.getInstance();
        selectedDate = LocalDate.now();
        txtDate = findViewById(R.id.txtSummaryDate);
        txtTotal = findViewById(R.id.txtSummaryTotal);
        Button btnPrev = findViewById(R.id.btnSummaryPrev);
        Button btnNext = findViewById(R.id.btnSummaryNext);
        // recycler view for scrollable list of summary rows
        RecyclerView recycler = findViewById(R.id.recyclerSummary);
        recycler.setLayoutManager(new LinearLayoutManager(this)); // tell the RecyclerView how to arrange items (vertical scrolling list)
        adapter = new SummaryAdapter(); // create the adapter that will supply data (summary rows)
        recycler.setAdapter(adapter); // RecyclerView will ask the adapter for rows to display

        // adjust calendar
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
        List<SummaryRow> rows = SummaryCalculator.compute(logsForDay); // compute summary rows
        adapter.setItems(rows);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
