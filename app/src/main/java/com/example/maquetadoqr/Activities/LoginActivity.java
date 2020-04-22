package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.maquetadoqr.POJOs.POJOUserLogin;
import com.example.maquetadoqr.R;
import com.example.maquetadoqr.Utils.SCChecklist;
import com.example.maquetadoqr.Utils.SCEvent;
import com.example.maquetadoqr.Utils.SCEventConfig;
import com.example.maquetadoqr.Utils.SCField;
import com.example.maquetadoqr.Utils.SCForm;
import com.example.maquetadoqr.Utils.SCJourneyTravel;
import com.example.maquetadoqr.Utils.SCUserLogin;
import com.example.maquetadoqr.Utils.URLs;
import com.example.maquetadoqr.ViewModels.DataViewModel;
import com.example.maquetadoqr.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public VolleySingleton volleySingleton;
    public DataViewModel dataViewModel;

    public TextView textViewFeedback;
    public EditText editTextUser;
    public EditText editTextPassword;
    public Button buttonLogin;

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

        dataViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(DataViewModel.class);

        bindUI();
    }

    public void bindUI() {
        textViewFeedback = findViewById(R.id.textViewFeedback);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(onClickListener);
    }

    public void goToScannerActivity() {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    public void login() {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        loginRequest(user, password);
        configRequest();

        SCUserLogin userLogin = SCUserLogin.getInstance();

        if(userLogin.getUserId() != 0) {
            goToScannerActivity();
        }
    }

    public void showUserPasswordError() {
        textViewFeedback.setTextColor(Color.RED);
        textViewFeedback.setText("Usuario o Contraseña inconrrecta!");
    }

    public void showServerError() {
        textViewFeedback.setTextColor(Color.RED);
        textViewFeedback.setText("LoginRequest: Server Error");
    }

    private void configRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.RESOURCES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            Log.d(TAG, "jsonArray.length(): " + jsonArray.length());

                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                                JSONObject checklist = (jsonObject.isNull("checklist") ? new JSONObject("{}") : jsonObject.getJSONObject("checklist"));
                                JSONObject form = (jsonObject.isNull("form") ? new JSONObject("{}"): jsonObject.getJSONObject("form"));
                                JSONArray journeyTravel = (jsonObject.isNull("journey_travel") ? new JSONArray("[{}]") : jsonObject.getJSONArray("journey_travel"));
                                Boolean isAuthorized = (jsonObject.isNull("isauthorized") ? false : jsonObject.getBoolean("isauthorized"));
                                Integer order = (jsonObject.isNull("order") ? 0 : jsonObject.getInt("order"));
                                Integer featureId = (jsonObject.isNull("feature_id") ? 0 : jsonObject.getInt("feature_id")); // event_id
                                String featureKey = (jsonObject.isNull("feature_key") ? "" : jsonObject.getString("feature_key"));
                                String resourceName = (jsonObject.isNull("resource_name") ? "" : jsonObject.getString("resource_name"));

                                if(!jsonObject.isNull("form")) {
                                    JSONArray field = form.getJSONArray("field");
                                    SCForm scForm = new SCForm();

                                    for(int j =0; j < field.length(); j++) {
                                        JSONObject jsonField = new JSONObject(field.get(j).toString());

                                        String name = (jsonField.isNull("name") ? "" : jsonField.getString("name"));
                                        String label = (jsonField.isNull("label") ? "" : jsonField.getString("label"));
                                        String type = (jsonField.isNull("type") ? "" : jsonField.getString("type"));
                                        Boolean visible = (jsonField.isNull("visible") ? false : jsonField.getBoolean("visible"));
                                        Boolean readOnly = (jsonField.isNull("readOnly") ? false : jsonField.getBoolean("readOnly"));
                                        Boolean required = (jsonField.isNull("required") ? false : jsonField.getBoolean("required"));

                                        scForm.addField(new SCField(visible, readOnly, required, name, label, type));
                                    }
                                    SCEvent scEvent = new SCEvent(featureId, order, featureKey, resourceName, isAuthorized, scForm, new SCChecklist(), new SCJourneyTravel());
                                    SCEventConfig scEventConfig = SCEventConfig.getInstance();
                                    scEventConfig.addEvent(scEvent);
                                    Log.d(TAG, "******************");
                                }
                            }
                        } catch (JSONException err) {
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
        }) {
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

        volleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void loginRequest(String user, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            SCUserLogin userLogin = SCUserLogin.getInstance();

                            // Extract data received from JSON
                            userLogin.setToken(jsonObject.getString("token"));
                            userLogin.setUserId(jsonObject.getInt("user_id"));
                            userLogin.setRoleFlow(jsonObject.getString("role_flow"));
                            userLogin.setUserName(jsonObject.getString("user_name"));
                            userLogin.setCompanyName(jsonObject.getString("company_name"));

                        } catch (JSONException err) {
                            // User/Password error
                            showUserPasswordError();
                            Log.e(TAG, err.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Server error
                showServerError();
                Log.e(TAG, error.getMessage());
            }
        }) {
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
                params.put("user", "obuendiaz");
                params.put("password", "Password135$");

                return params;
            }
        };

        volleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
