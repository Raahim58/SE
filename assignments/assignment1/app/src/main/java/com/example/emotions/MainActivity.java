//package com.example.emotions;
//
//import android.os.Bundle;
//
//import com.google.android.material.snackbar.Snackbar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.view.View;
//
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.example.emotions.databinding.ActivityMainBinding;
//
//import android.view.Menu;
//import android.view.MenuItem;
//
//public class MainActivity extends AppCompatActivity {
//
//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}

package com.example.emotions;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;

/*
  main screen of the app.
  user taps emotion buttons to log entries.
  logs are stored in LogRepository.
*/
public class MainActivity extends AppCompatActivity {

    private LogRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = LogRepository.getInstance();

        Button btnHappy = findViewById(R.id.btnHappy);
        Button btnSad = findViewById(R.id.btnSad);
        Button btnGrateful = findViewById(R.id.btnGrateful);
        Button btnAngry = findViewById(R.id.btnAngry);
        Button btnExcited = findViewById(R.id.btnExcited);
        Button btnStressed = findViewById(R.id.btnStressed);

        Button btnViewLogs = findViewById(R.id.btnViewLogs);
        Button btnViewSummary = findViewById(R.id.btnViewSummary);

        btnHappy.setOnClickListener(v -> logEmotion(EmotionType.HAPPY));
        btnSad.setOnClickListener(v -> logEmotion(EmotionType.SAD));
        btnGrateful.setOnClickListener(v -> logEmotion(EmotionType.GRATEFUL));
        btnAngry.setOnClickListener(v -> logEmotion(EmotionType.ANGRY));
        btnExcited.setOnClickListener(v -> logEmotion(EmotionType.EXCITED));
        btnStressed.setOnClickListener(v -> logEmotion(EmotionType.STRESSED));

        btnViewLogs.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LogsActivity.class);
            startActivity(i);
        });

        btnViewSummary.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(i);
        });

    }

    private void logEmotion(EmotionType type) {
        repo.add(new LogEntry(type, Instant.now()));
        Toast.makeText(this,
                "logged " + type.getEmoji() + " " + type.getLabel(),
                Toast.LENGTH_SHORT).show();
    }
}
