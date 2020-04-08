package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.maquetadoqr.R;

public class JourneyDetailActivity extends AppCompatActivity {

    public ImageView imageViewAvatar;
    public Button buttonChecklist;
    public Button buttonSend;
    public Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_detail);
    }

    public void bindUI() {
        imageViewAvatar = findViewById(R.id.journeyImageViewAvatar);
        buttonChecklist = findViewById(R.id.journeyButtonChecklist);
        buttonSend = findViewById(R.id.journeyButtonSend);
        buttonCancel = findViewById(R.id.journeyButtonCancel);
    }

    public void buttonCancelFunction(View view) {
        finish();
    }

    public void buttonChecklistFunction() {

    }

    public void goToEventFormActivity(View view) {
        Intent intent = new Intent(this, EventFormActivity.class);
        startActivity(intent);
    }
}

// TODO: Avatar img - Picasso
