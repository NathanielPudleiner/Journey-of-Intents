package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvCellStatus = findViewById(R.id.tvCellStatus);
        Button btnPickLock = findViewById(R.id.btnPickLock);
        Button btnSearchDesk = findViewById(R.id.btnSearchDesk);

        String statusMsg;
        if (gadgetsFound >= 2) {
            statusMsg = String.format("Agent %s status: Well Equipped Cell Break", agentName);
        } else if (gadgetsFound == 1) {
            statusMsg = String.format("Agent %s status: Partially Equipped", agentName);
        } else {
            statusMsg = String.format("Agent %s status: Unarmed Holding Cell", agentName);
        }
        tvCellStatus.setText(statusMsg);

        ArrayList<String> finalInventory = inventory;
        btnPickLock.setOnClickListener(v -> {
            finalInventory.add("Lockpick");
            Intent intent = new Intent(CellActivity.this, HallwayActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnSearchDesk.setOnClickListener(v -> {
            finalInventory.add("Flashlight");
            int updatedGadgets = gadgetsFound + 1;
            Intent intent = new Intent(CellActivity.this, MessHallActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", updatedGadgets);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}