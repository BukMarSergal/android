package com.example.bigprace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity4 extends AppCompatActivity {

    private ImageView imageView;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livefeed);

        // Set up action bar with back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Initialize ImageView
        imageView = findViewById(R.id.imageView);

        // Load a random image into ImageView
        loadRandomImage();
    }

    private void loadRandomImage() {
        // Generate a random number between 1 and 30 (inclusive)
        int randomIndex = random.nextInt(4) + 1;

        // Construct the image name with "a" prefix and random index
        String imageName = "a" + randomIndex;

        // Get the resource ID of the random image
        int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        // Set the random image to ImageView
        imageView.setImageResource(imageResourceId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity3
            finish(); // Close MainActivity4 and return to MainActivity3
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
