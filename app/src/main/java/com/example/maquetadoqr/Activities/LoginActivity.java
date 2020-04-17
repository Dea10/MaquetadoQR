package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.example.maquetadoqr.POJOs.POJOEventConfig;
import com.example.maquetadoqr.POJOs.POJOField;
import com.example.maquetadoqr.POJOs.POJOForm;
import com.example.maquetadoqr.POJOs.POJOUserLogin;
import com.example.maquetadoqr.R;
import com.example.maquetadoqr.ViewModels.UserLoginViewModel;
import com.example.maquetadoqr.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public VolleySingleton volleySingleton;
    public UserLoginViewModel userLoginViewModel;

    public TextView textViewFeedback;
    public EditText editTextUser;
    public EditText editTextPassword;
    public Button buttonLogin;

    public String token;
    public Integer userId;
    public String roleFlow;
    public String userName;
    public String companyName;

    public static final String TAG = LoginActivity.class.getName();
    public static final String EXTRA_TOKEN = "EXTRA_TOKEN";

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

        userLoginViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserLoginViewModel.class);

        bindUI();
    }

    public void bindUI() {
        textViewFeedback = findViewById(R.id.textViewFeedback);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(onClickListener);
    }

    public void goToScannerActivity(String token) {
        Intent intent = new Intent(this, ScannerActivity.class);
        intent.putExtra(EXTRA_TOKEN, token);
        startActivity(intent);
    }

    public void login() {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        String loginUrl = "http://11994.qa.rcontrol.com.mx/user/LoginQR";
        String resourcesUrl = "http://11994.qa.rcontrol.com.mx/japi/get_feature_configuration_by_user_and_category";

        loginRequest(user, password, loginUrl);
        configRequest(resourcesUrl);
    }

    public void showUserPasswordError() {
        textViewFeedback.setTextColor(Color.RED);
        textViewFeedback.setText("Usuario o Contraseña inconrrecta!");
    }

    public void showServerError() {
        textViewFeedback.setTextColor(Color.RED);
        textViewFeedback.setText("LoginRequest: Server Error");
    }

    private void configRequest(String resourcesUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, resourcesUrl,
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

                                // TODO: Send eventConfig to DB
                                userLoginViewModel.insertEventConfig(new POJOEventConfig(featureId, order, featureKey, resourceName, isAuthorized));

                                // TODO: Extract form data
                                if(!jsonObject.isNull("form")) {
                                    JSONArray field = form.getJSONArray("field");
                                    // maybe a good moment to send a form related to event
                                    userLoginViewModel.insertForm(new POJOForm(featureId));
                                    for(int j =0; j < field.length(); j++) {
                                        JSONObject jsonField = new JSONObject(field.get(j).toString());

                                        String name = (jsonField.isNull("name") ? "" : jsonField.getString("name"));
                                        String label = (jsonField.isNull("label") ? "" : jsonField.getString("label"));
                                        String type = (jsonField.isNull("type") ? "" : jsonField.getString("type"));
                                        Boolean visible = (jsonField.isNull("visible") ? false : jsonField.getBoolean("visible"));
                                        Boolean readOnly = (jsonField.isNull("readOnly") ? false : jsonField.getBoolean("readOnly"));
                                        Boolean required = (jsonField.isNull("required") ? false : jsonField.getBoolean("required"));

                                        // TODO: Send form to DB in a FieldObject relating to a Form
                                        // send field related to form
                                        Integer lastFormId = userLoginViewModel.getLastFormId();
                                        userLoginViewModel.insertField(new POJOField(visible, readOnly, required, name, label, type, lastFormId));
                                    }
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

    //TODO: Separar lógica de login, mandar petición a DB a repositorio
    /*Intenté separar la lógica del login, pero al retornar un objeto tuve complicaciones con los métodos onResponse de Volley*/

    public void loginRequest(String user, String password, String loginUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Extract data received from JSON
                            token = jsonObject.getString("token");
                            userId = jsonObject.getInt("user_id");
                            roleFlow = jsonObject.getString("role_flow");
                            userName = jsonObject.getString("user_name");
                            companyName = jsonObject.getString("company_name");

                            // Build and send data to Room DB
                            userLoginViewModel.insertUserLogin(new POJOUserLogin(token, userId, roleFlow, userName, companyName));

                            // goToScannerActivity(token);
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
