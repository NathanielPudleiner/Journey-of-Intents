package com.example.journeyofintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CommCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_center);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvCommStatus = findViewById(R.id.tvCommStatus);
        Button btnOpenWebIntel = findViewById(R.id.btnOpenWebIntel);
        Button btnGoLab = findViewById(R.id.btnGoLab);
        Button btnGoArmoryFromComm = findViewById(R.id.btnGoArmoryFromComm);

        String commStatus;
        if (inventory.contains("Lockpick") && gadgetsFound > 0) {
            commStatus = String.format("Comm Center Terminal unlocked with Lockpick for %s!", agentName);
        } else if (hasSecurityPass) {
            commStatus = String.format("Comm Center Terminal unlocked with Security Pass for %s!", agentName);
        } else {
            commStatus = String.format("Comm Center Terminal restricted access for Agent %s.", agentName);
        }
        tvCommStatus.setText(commStatus);

        btnOpenWebIntel.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nasa.gov"));
            startActivity(webIntent);
        });

        ArrayList<String> finalInventory = inventory;
        btnGoLab.setOnClickListener(v -> {
            Intent intent = new Intent(CommCenterActivity.this, LabActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnGoArmoryFromComm.setOnClickListener(v -> {
            Intent intent = new Intent(CommCenterActivity.this, ArmoryActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}