package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvLabStory = findViewById(R.id.tvLabStory);
        Button btnStealLaser = findViewById(R.id.btnStealLaser);
        Button btnPressRedButton = findViewById(R.id.btnPressRedButton);

        if (inventory.size() >= 2) {
            tvLabStory.setText(String.format("Agent %s, your inventory gives you an advantage in the lab.", agentName));
        } else {
            tvLabStory.setText(String.format("Agent %s, you enter the dangerous lab with minimal equipment.", agentName));
        }

        ArrayList<String> finalInventory = inventory;
        btnStealLaser.setOnClickListener(v -> {
            finalInventory.add("Laser Cutter");
            int updatedGadgets = gadgetsFound + 1;
            Intent intent = new Intent(LabActivity.this, RoofActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", updatedGadgets);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnPressRedButton.setOnClickListener(v -> {
            Intent intent = new Intent(LabActivity.this, GameOverActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}