package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MessHallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_hall);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        if (!hasSecurityPass) {
            hasSecurityPass = true;
            inventory.add("Security Pass");
            Toast.makeText(MessHallActivity.this, "Security Pass acquired!", Toast.LENGTH_SHORT).show();
        }

        TextView tvMessStatus = findViewById(R.id.tvMessStatus);
        Button btnGoArmory = findViewById(R.id.btnGoArmory);
        Button btnGoCommCenter = findViewById(R.id.btnGoCommCenter);

        String passStatus;
        if (inventory.contains("Security Pass")) {
            passStatus = String.format("Security Pass Status: ACTIVE for %s", agentName);
        } else if (gadgetsFound > 0) {
            passStatus = String.format("Security Pass Status: VERIFIED for %s", agentName);
        } else {
            passStatus = String.format("Security Pass Status: PENDING for %s", agentName);
        }
        tvMessStatus.setText(passStatus);

        boolean updatedPass = hasSecurityPass;
        ArrayList<String> finalInventory = inventory;
        btnGoArmory.setOnClickListener(v -> {
            Intent intent = new Intent(MessHallActivity.this, ArmoryActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", updatedPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnGoCommCenter.setOnClickListener(v -> {
            Intent intent = new Intent(MessHallActivity.this, CommCenterActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", updatedPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}