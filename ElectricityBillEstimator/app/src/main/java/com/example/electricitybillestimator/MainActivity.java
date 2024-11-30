package com.example.electricitybillestimator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public EditText unitUsed, rebatePercent;
    public TextView tvFinalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize views
        unitUsed = findViewById(R.id.unitUsed);
        rebatePercent = findViewById(R.id.rebatePercent);
        tvFinalCost = findViewById(R.id.tvFinalCost);
        Button calBills = findViewById(R.id.calBills);
        Button clearBills = findViewById(R.id.clearBills);

        // Set up button click listeners
        calBills.setOnClickListener(v -> calculateBill());

        clearBills.setOnClickListener(v -> {
            unitUsed.setText("");
            rebatePercent.setText("");
            tvFinalCost.setText(""); // Clear the result text view
            Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuBill) {
            Toast.makeText(this, "You are on this page", Toast.LENGTH_SHORT).show();
            return true;
        } else if (selected == R.id.menuAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateBill() {
        String unitsStr = unitUsed.getText().toString();
        String rebateStr = rebatePercent.getText().toString();

        if (TextUtils.isEmpty(unitsStr) || TextUtils.isEmpty(rebateStr)) {
            tvFinalCost.setText("Please enter all fields!");
            return;
        }

        double units = Double.parseDouble(unitsStr);
        double rebate = Double.parseDouble(rebateStr);

        if (rebate < 0 || rebate > 5) {
            tvFinalCost.setText("Rebate percentage must be between 0 and 5.");
            return;
        }

        double totalCharges = 0;

        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        double finalCost = totalCharges - (totalCharges * rebate / 100);
        tvFinalCost.setText(String.format("Total Charges: RM %.2f\nFinal Cost (after rebate): RM %.2f", totalCharges, finalCost));
    }
}
