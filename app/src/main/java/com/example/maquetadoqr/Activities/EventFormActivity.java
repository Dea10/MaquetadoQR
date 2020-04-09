package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maquetadoqr.R;

public class EventFormActivity extends AppCompatActivity {

    public Button buttonChecklist;
    public Button buttonSend;
    public Button buttonCancel;

    public static final String EXTRA_EVENTFORM_ACTIVITY = "EXTRA_EVENTFORM_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        bindUI();
    }

    public void bindUI() {
        buttonChecklist = findViewById(R.id.eventFormButtonChecklist);
        buttonSend = findViewById(R.id.eventFormButtonSend);
        buttonCancel = findViewById(R.id.eventFormButtonCancel);
    }

    public void goToAlertActivity(View view) {
        Intent intent = new Intent(this, AlertActivity.class);
        intent.putExtra(EXTRA_EVENTFORM_ACTIVITY, true);
        startActivity(intent);
    }

    public void buttonCancelFunction(View view) {
        finish();
    }

    public void goToChecklistActivity(View view) {
        Intent intent = new Intent(this, ChecklistActivity.class);
        startActivity(intent);
    }
}
