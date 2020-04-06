package com.example.maquetadoqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    public Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();
    }

    public void bindUI() {
        login_button = findViewById(R.id.login_button);
    }

    public void goToScannerActivity(View view) {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }
}
