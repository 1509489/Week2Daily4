package com.pixelart.week2daily4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddCelebrity(View view) {
        Intent intent = new Intent(this, AddCelebrityActivity.class);
        startActivity(intent);
    }

    public void onViewCelebrity(View view) {
        Intent intent = new Intent(this, ViewCelebrityActivity.class);
        startActivity(intent);
    }

    public void onViewFavorite(View view) {
        Intent intent = new Intent(this, FavoriteCelebrityActivity.class);
        startActivity(intent);
    }
}
