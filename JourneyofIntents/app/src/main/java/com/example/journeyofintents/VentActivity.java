package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class VentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vent);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvVentStory = findViewById(R.id.tvVentStory);
        Button btnClimbToRoof = findViewById(R.id.btnClimbToRoof);
        Button btnDropGuardRoom = findViewById(R.id.btnDropGuardRoom);

        if (gadgetsFound > 0) {
            tvVentStory.setText("You use your gadgets to quietly navigate the tight air vents.");
        } else {
            tvVentStory.setText("It is pitch dark inside the vent shaft and hard to move!");
        }

        ArrayList<String> finalInventory = inventory;
        btnClimbToRoof.setOnClickListener(v -> {
            Intent intent = new Intent(VentActivity.this, RoofActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });

        btnDropGuardRoom.setOnClickListener(v -> {
            Intent intent = new Intent(VentActivity.this, GameOverActivity.class);
            intent.putExtra("agentName", agentName);
            intent.putExtra("gadgetsFound", gadgetsFound);
            intent.putExtra("hasSecurityPass", hasSecurityPass);
            intent.putStringArrayListExtra("inventory", finalInventory);
            startActivity(intent);
        });
    }
}