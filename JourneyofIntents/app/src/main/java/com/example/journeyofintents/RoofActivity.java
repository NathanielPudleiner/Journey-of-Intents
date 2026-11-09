package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RoofActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roof);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);
        boolean hasSecurityPass = getIntent().getBooleanExtra("hasSecurityPass", false);
        ArrayList<String> inventory = getIntent().getStringArrayListExtra("inventory");
        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        TextView tvRoofStory = findViewById(R.id.tvRoofStory);
        Button btnBoardChopper = findViewById(R.id.btnBoardChopper);
        Button btnZiplineWater = findViewById(R.id.btnZiplineWater);

        if (hasSecurityPass && gadgetsFound >= 2) {
            tvRoofStory.setText(String.format("Agent %s, you reach the helipad fully prepared to escape!", agentName));
        } else {
            tvRoofStory.setText(String.format("Agent %s, you reach the roof but guards are everywhere!", agentName));
        }

        ArrayList<String> finalInventory = inventory;

        btnBoardChopper.setOnClickListener(v -> {
            if (hasSecurityPass) {
                Intent intent = new Intent(RoofActivity.this, EscapeActivity.class);
                intent.putExtra("agentName", agentName);
                intent.putExtra("gadgetsFound", gadgetsFound);
                intent.putExtra("hasSecurityPass", hasSecurityPass);
                intent.putStringArrayListExtra("inventory", finalInventory);
                startActivity(intent);
            } else {
                Intent intent = new Intent(RoofActivity.this, GameOverActivity.class);
                intent.putExtra("agentName", agentName);
                intent.putExtra("gadgetsFound", gadgetsFound);
                intent.putExtra("hasSecurityPass", hasSecurityPass);
                intent.putStringArrayListExtra("inventory", finalInventory);
                startActivity(intent);
            }
        });

        btnZiplineWater.setOnClickListener(v -> {
            if (gadgetsFound >= 1) {
                Intent intent = new Intent(RoofActivity.this, EscapeActivity.class);
                intent.putExtra("agentName", agentName);
                intent.putExtra("gadgetsFound", gadgetsFound);
                intent.putExtra("hasSecurityPass", hasSecurityPass);
                intent.putStringArrayListExtra("inventory", finalInventory);
                startActivity(intent);
            } else {
                Intent intent = new Intent(RoofActivity.this, GameOverActivity.class);
                intent.putExtra("agentName", agentName);
                intent.putExtra("gadgetsFound", gadgetsFound);
                intent.putExtra("hasSecurityPass", hasSecurityPass);
                intent.putStringArrayListExtra("inventory", finalInventory);
                startActivity(intent);
            }
        });
    }
}