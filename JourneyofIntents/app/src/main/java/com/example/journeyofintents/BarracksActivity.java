package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BarracksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barracks);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvBarracksStory = findViewById(R.id.tvBarracksStory);
        Button btnCrawlVent = findViewById(R.id.btnCrawlVent);
        Button btnRushComm = findViewById(R.id.btnRushComm);

        if (hasSecurityPass || gadgetsFound >= 2) {
            tvBarracksStory.setText("Guards pass by without noticing you because of your pass or gadgets!");
        } else {
            tvBarracksStory.setText("You quietly tiptoe past sleeping guards in the dark.");
        }

        ArrayList<String> finalInventory = inventory;
        btnCrawlVent.setOnClickListener(v -> {
            Intent intent = new Intent(BarracksActivity.this, VentActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnRushComm.setOnClickListener(v -> {
            Intent intent = new Intent(BarracksActivity.this, CommCenterActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}