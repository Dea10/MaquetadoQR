package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.maquetadoqr.R;

public class ChecklistActivity extends AppCompatActivity {

    public Button buttonSend;
    public Button buttonCancel;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        bindUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void bindUI() {
        buttonSend = findViewById(R.id.checklistButtonSend);
        buttonCancel = findViewById(R.id.checklistButtonCancel);
        spinner = findViewById(R.id.checklistSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.test_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void buttonCancelFunction(View view) {
        finish();
    }

    public void goToAlertActivity(View view) {
        Intent intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }
}
