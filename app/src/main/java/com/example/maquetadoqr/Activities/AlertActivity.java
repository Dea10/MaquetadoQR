package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maquetadoqr.R;

public class AlertActivity extends AppCompatActivity {

    public Boolean eventFormExtra;

    public Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        eventFormExtra = getIntent().getBooleanExtra(EventFormActivity.EXTRA_EVENTFORM_ACTIVITY, false);

        bindUI();
    }

    public void bindUI() {
        buttonContinue = findViewById(R.id.alertButtonContinue);
    }

    public void buttonContinueFunction(View view) {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }
}