package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ArmoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armory);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        if (!inventory.contains("Smoke Bomb")) {
            gadgetsFound = gadgetsFound + 1;
            inventory.add("Smoke Bomb");
            Toast.makeText(ArmoryActivity.this, "Smoke Bomb found in Armory!", Toast.LENGTH_SHORT).show();
        }

        TextView tvArmoryStatus = findViewById(R.id.tvArmoryStatus);
        Button btnArmoryToVent = findViewById(R.id.btnArmoryToVent);
        Button btnArmoryToRoof = findViewById(R.id.btnArmoryToRoof);

        String armoryStatus;
        if (gadgetsFound >= 3) {
            armoryStatus = String.format("Agent %s armory status: Fully Armed (%d gadgets)", agentName, gadgetsFound);
        } else if (gadgetsFound >= 1) {
            armoryStatus = String.format("Agent %s armory status: Moderately Armed (%d gadgets)", agentName, gadgetsFound);
        } else {
            armoryStatus = String.format("Agent %s armory status: Unarmed", agentName);
        }
        tvArmoryStatus.setText(armoryStatus);

        int finalGadgets = gadgetsFound;
        ArrayList<String> finalInventory = inventory;

        btnArmoryToVent.setOnClickListener(v -> {
            Intent intent = new Intent(ArmoryActivity.this, VentActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", finalGadgets);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnArmoryToRoof.setOnClickListener(v -> {
            Intent intent = new Intent(ArmoryActivity.this, RoofActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", finalGadgets);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}