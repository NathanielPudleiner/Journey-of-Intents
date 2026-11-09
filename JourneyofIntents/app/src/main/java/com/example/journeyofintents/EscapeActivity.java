package com.example.journeyofintents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EscapeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escape);

        String agentName = getIntent().getStringExtra("agentName");
        int gadgetsFound = getIntent().getIntExtra("gadgetsFound", 0);

        TextView tvEscapeMsg = findViewById(R.id.tvEscapeMsg);
        TextView tvEscapeDetails = findViewById(R.id.tvEscapeDetails);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);

        tvEscapeMsg.setText(String.format("MISSION ACCOMPLISHED! Agent %s escaped with %d gadgets!", agentName, gadgetsFound));

        if (gadgetsFound >= 2) {
            tvEscapeDetails.setText("You pulled off a legendary high-tech escape!");
        } else {
            tvEscapeDetails.setText("You barely made it out alive, but a win is a win!");
        }

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(EscapeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}