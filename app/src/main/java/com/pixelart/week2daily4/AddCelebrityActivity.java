package com.pixelart.week2daily4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCelebrityActivity extends AppCompatActivity {

    private EditText etName, etAge, etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celebrity);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
    }

    public void AddCelebrity(View view) {
        String name =  etName.getText().toString();
        String age = etAge.getText().toString();
        String weight = etWeight.getText().toString();

        if (!name.equals("") && !age.equals("") && !weight.equals(""))
        {
            Celebrity celebrity = new Celebrity(name, age, weight);
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.addCelebrity(celebrity);

            Toast.makeText(this, "Celebrity Added", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();

    }
}
