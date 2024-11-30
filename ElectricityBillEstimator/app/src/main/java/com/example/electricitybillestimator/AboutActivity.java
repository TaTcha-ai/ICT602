package com.example.electricitybillestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up the toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.me);

        TextView aboutText = findViewById(R.id.tvAbout);
        aboutText.setText("Developer: Nurul Ain Natasya Binti Othman\n" +
                "Student Number: 2022610424\n" +
                "Programme Code: CDCS240\n\n" +
                "Copyright 2024. All Rights Reserved.\n" +
                "Source Code: https://github.com/YourGitHubRepo");
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

        if (selected ==R.id.menuAbout) {
            Toast.makeText(this, "You are on this page", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (selected == R.id.menuBill){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
