package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maquetadoqr.R;

public class AssignActivity extends AppCompatActivity {

    public Button buttonSend;
    public Button buttonCancel;
    public EditText editTextCredential;
    public EditText editTextFullName;
    public EditText editTextTransLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        bindUI();
    }

    public void bindUI() {
        buttonSend = findViewById(R.id.assignButtonSend);
        buttonCancel = findViewById(R.id.assignButtonCancel);
        editTextCredential = findViewById(R.id.assignEditTextCredential);
        editTextFullName = findViewById(R.id.assignEditTextFullName);
        editTextTransLine = findViewById(R.id.assignEditTextTransLine);
    }

    public void buttonCancelFunction(View view) {
        finish();
    }

    public void goToJourneyDetailActivity(View view) {
        Intent intent = new Intent(this, JourneyDetailActivity.class);
        startActivity(intent);
    }
}
