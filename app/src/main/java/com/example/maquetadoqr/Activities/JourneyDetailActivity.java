package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.maquetadoqr.R;
import com.squareup.picasso.Picasso;

public class JourneyDetailActivity extends AppCompatActivity {

    public ImageView imageViewAvatar;
    public Button buttonChecklist;
    public Button buttonSend;
    public Button buttonCancel;

    public String urlImg = "https://png.pngtree.com/png-clipart/20190924/original/pngtree-businessman-user-avatar-free-vector-png-image_4827807.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_detail);

        bindUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.get().load(urlImg).into(imageViewAvatar);
    }

    public void bindUI() {
        imageViewAvatar = findViewById(R.id.journeyImageViewAvatar);
        buttonSend = findViewById(R.id.journeyButtonSend);
        buttonCancel = findViewById(R.id.journeyButtonCancel);
    }

    public void buttonCancelFunction(View view) {
        finish();
    }

    public void goToEventFormActivity(View view) {
        Intent intent = new Intent(this, EventFormActivity.class);
        startActivity(intent);
    }
}

// TODO: Send buttons
// TODO: Cancel buttons
