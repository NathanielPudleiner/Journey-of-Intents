package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);

        TextView tvGameOverMsg = findViewById(R.id.tvGameOverMsg);
        TextView tvGameOverDetails = findViewById(R.id.tvGameOverDetails);
        Button btnTryAgain = findViewById(R.id.btnTryAgain);

        if (agentName != null) {
            tvGameOverMsg.setText(String.format("MISSION FAILED! Agent %s was captured by enemy guards.", agentName));
        } else {
            tvGameOverMsg.setText("MISSION FAILED! You were captured.");
        }

        if (gadgetsFound == 0) {
            tvGameOverDetails.setText("You had 0 gadgets in your inventory to help you defend yourself.");
        } else {
            tvGameOverDetails.setText(String.format("Even with %d gadgets, the enemy overpowered you.", gadgetsFound));
        }

        btnTryAgain.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}