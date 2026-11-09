package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HallwayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hallway);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvHallwayInventory = findViewById(R.id.tvHallwayInventory);
        TextView tvHallwayStory = findViewById(R.id.tvHallwayStory);
        Button btnGoMessHall = findViewById(R.id.btnGoMessHall);
        Button btnGoBarracks = findViewById(R.id.btnGoBarracks);

        String inventoryText = "Inventory:\n";
        for (int i = 0; i < inventory.size(); i++) {
            inventoryText = inventoryText + "- " + inventory.get(i) + "\n";
        }
        tvHallwayInventory.setText(inventoryText);

        if (hasSecurityPass && gadgetsFound > 0) {
            tvHallwayStory.setText(String.format("Agent %s, you sneak through carrying %d gadgets and a pass.", agentName, gadgetsFound));
        } else {
            tvHallwayStory.setText(String.format("Agent %s, you enter the hallway carefully with %d gadgets.", agentName, gadgetsFound));
        }

        ArrayList<String> finalInventory = inventory;
        btnGoMessHall.setOnClickListener(v -> {
            Intent intent = new Intent(HallwayActivity.this, MessHallActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnGoBarracks.setOnClickListener(v -> {
            Intent intent = new Intent(HallwayActivity.this, BarracksActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}