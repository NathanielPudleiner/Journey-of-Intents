package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etAgentName = findViewById(R.id.etAgentName);
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            String agentName = etAgentName.getText().toString();
            if (agentName.trim().isEmpty()) {
                agentName = "Agent Smith";
            }

            int gadgetsFound = 0;
            boolean hasSecurityPass = false;
            ArrayList<String> inventory = new ArrayList<>();
            inventory.add("Badge");

            Intent intent = new Intent(MainActivity.this, CellActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", inventory);
            startActivity(intent);
        });
    }
}