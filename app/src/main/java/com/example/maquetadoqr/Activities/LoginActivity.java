package com.example.maquetadoqr.Activities;

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
import com.example.maquetadoqr.R;
import com.example.maquetadoqr.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    public String userId;
    public String roleFlow;
    public String userName;
    public String companyName;

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
        // RequestQueue queue = Volley.newRequestQueue(this);
        // String url ="https://www.rcontrol.com.mx/RpcUser/get_token_by_user";

        String loginUrl = "http://11994.qa.rcontrol.com.mx/user/LoginQR";
        String resourcesUrl = "http://11994.qa.rcontrol.com.mx/japi/get_feature_configuration_by_user_and_category";

        loginRequest(loginUrl);
        configRequest(resourcesUrl);

        //TODO: Mandar data a DB
    }

    private void configRequest(String resourcesUrl) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, resourcesUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            //JSONObject jsonObject = new JSONObject(response);
                            //JSONArray array = jsonObject.getJSONArray("data");
                            ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();

                            // TODO: Recibir JSON y extraer data

                        }catch (JSONException err){
                            // Handle JSONException

                            Log.e(TAG, err.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewFeedback.setTextColor(Color.RED);
                textViewFeedback.setText("ConfigRequest: Server Error");
                Log.e(TAG, error.getMessage());
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
                params.put("token", "a0c272f8-6016-4e2b-a515-7e03ed200c56");
                params.put("parameters", "{\"feature_category_key\":[\"REGISTER\",\"QRTERMINAL\"]}");

                return params;
            }
        };

        // Add the request to the RequestQueue.
        // queue.add(stringRequest);

        volleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void loginRequest(String loginUrl) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //JSONArray array = jsonObject.getJSONArray("data");

                            //token = array.getJSONArray(0).get(0).toString();

                            // TODO: Recibir JSON y extraer data

                            token = jsonObject.getString("token");
                            userId = jsonObject.getString("user_id");
                            roleFlow = jsonObject.getString("role_flow");
                            userName = jsonObject.getString("user_name");
                            companyName = jsonObject.getString("company_name");

                            // TODO: Mandar data a DB

                            // goToScannerActivity(user, password, token);
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
                textViewFeedback.setText("LoginRequest: Server Error");
                Log.e(TAG, error.getMessage());
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
                params.put("user", "admin_qa_rc");
                params.put("password", "Password135$$");

                return params;
            }
        };

        // Add the request to the RequestQueue.
        // queue.add(stringRequest);

        volleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
