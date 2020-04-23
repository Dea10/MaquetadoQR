package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maquetadoqr.R;
import com.example.maquetadoqr.Utils.URLs;
import com.squareup.picasso.Picasso;

public class ScannerActivityInbound extends AppCompatActivity {

    public AnimationDrawable scannerAnimation;
    public ImageView imageViewHeader;
    public ImageView imageViewLogo;
    public ImageView imageViewAnimation;
    public TextView textViewCompanyName;
    public TextView textViewCounter;

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_inbound);

        bindUI();

        imageViewAnimation.setBackgroundResource(R.drawable.scanner_animation);
        scannerAnimation = (AnimationDrawable) imageViewAnimation.getBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerAnimation.start();
        cdt.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cdt.cancel();
    }

    public void bindUI() {
        imageViewHeader = findViewById(R.id.scannerInboundImageViewHeader);
        imageViewLogo = findViewById(R.id.scannerInboundImageViewLogo);
        imageViewAnimation = findViewById(R.id.scannerInboundImageViewAnimation);
        textViewCompanyName = findViewById(R.id.scannerInboundTextViewCompanyName);
        textViewCounter = findViewById(R.id.scannerInboundTextViewCounter);
    }

    public void setView() {
        Picasso.get().load(URLs.RC_LOGO).into(imageViewLogo);
    }

    public void logout(View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public CountDownTimer cdt = new CountDownTimer(30000, 1000) {
        public void onTick(long millisUntilFinished) {
            textViewCounter.setText("El scanner se reiniciará en: " + millisUntilFinished / 1000 + " segundos");
        }

        public void onFinish() {
            textViewCounter.setText("El scanner se está reiniciando ...");
            this.start();
        }
    };
}
