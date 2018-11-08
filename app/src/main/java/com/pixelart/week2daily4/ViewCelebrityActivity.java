package com.pixelart.week2daily4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class ViewCelebrityActivity extends AppCompatActivity {
    private static final String TAG = "ViewCelebrityActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private CelebrityListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_celebrity);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);



    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Celebrity> celebrities = databaseHelper.getCelebrity();

        for(Celebrity celebrity : celebrities)
        {
           // celebrities.add(celebrity);
            Log.d(TAG, "Celebrities " + celebrity.getName() + " " + celebrity.getAge() + " "
            + celebrity.getWeight());
        }

        adapter = new CelebrityListAdapter(celebrities);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
