package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maquetadoqr.R;
import com.squareup.picasso.Picasso;

public class ScannerActivity extends AppCompatActivity {

    public AnimationDrawable scanner_animation;
    public ImageView iv_header;
    public ImageView iv_logo;
    public ImageView iv_anim;
    public TextView tv_name;
    public TextView tv_counter;
    public Button test;

    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        bindUI();

        iv_anim.setBackgroundResource(R.drawable.scanner_animation);
        scanner_animation = (AnimationDrawable) iv_anim.getBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanner_animation.start();
        cdt.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cdt.cancel();
    }

    public void bindUI() {
        iv_header = findViewById(R.id.scanner_iv_header);
        iv_logo = findViewById(R.id.scanner_iv_logo);
        iv_anim = findViewById(R.id.scanner_iv_anim);
        tv_name = findViewById(R.id.scanner_tv_name);
        tv_counter = findViewById(R.id.scanner_tv_counter);
        test = findViewById(R.id.journeyButtonChecklist);
    }

    public void setView() {
        String company_logo = "https://rcontrol.com.mx/wp-content/uploads/2016/03/LOGO-RC-OK.png";
        Picasso.get().load(company_logo).into(iv_logo);
    }

    public void goToJourneyDetailActivity(View view) {
        intent = new Intent(this, JourneyDetailActivity.class);
        startActivity(intent);
    }

    public void goToAssignActivity(View view) {
        intent = new Intent(this, AssignActivity.class);
        startActivity(intent);
    }

    public void goToAlertActivity(View view) {
        intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }

    public CountDownTimer cdt = new CountDownTimer(30000, 1000) {
        public void onTick(long millisUntilFinished) {
            tv_counter.setText("El scanner se reiniciará en: " + millisUntilFinished / 1000 + " segundos");
        }

        public void onFinish() {
            tv_counter.setText("El scanner se está reiniciando ...");
            this.start();
        }
    };
}
