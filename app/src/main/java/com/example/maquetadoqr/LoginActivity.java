package com.example.maquetadoqr;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maquetadoqr.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public VolleySingleton volleySingleton;

    public TextView textViewFeedback;
    public EditText editTextUser;
    public EditText editTextPassword;
    public Button buttonLogin;

    public String user;
    public String password;
    public String token;
    public static final String TAG = LoginActivity.class.getName();

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();
    }

    public void bindUI() {
        textViewFeedback = findViewById(R.id.textViewFeedback);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(onClickListener);
    }

    public void goToScannerActivity(String user, String password, String token) {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    public void login() {

        user = editTextUser.getText().toString();
        password = editTextPassword.getText().toString();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.rcontrol.com.mx/RpcUser/get_token_by_user";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            token = array.getJSONArray(0).get(0).toString();

                            goToScannerActivity(user, password, token);
                        }catch (JSONException err){
                            // Handle JSONException
                            textViewFeedback.setTextColor(Color.RED);
                            textViewFeedback.setText("Usuario o Contraseña inconrrecta!");
                            Log.e(TAG, err.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewFeedback.setTextColor(Color.RED);
                textViewFeedback.setText("Server Error");
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put("user", user);
                params.put("password", password);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        // queue.add(stringRequest);

        volleySingleton.getmInstance(this).addToRequestQueue(stringRequest);
    }
}
