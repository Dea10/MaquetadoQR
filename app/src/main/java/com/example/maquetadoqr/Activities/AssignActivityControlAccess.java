package com.example.maquetadoqr.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.maquetadoqr.R;
import com.example.maquetadoqr.Utils.SCDriver;
import com.example.maquetadoqr.Utils.SCUserLogin;
import com.example.maquetadoqr.Utils.URLs;
import com.example.maquetadoqr.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AssignActivityControlAccess extends AppCompatActivity {

    public Button buttonSend;
    public Button buttonCancel;
    public Button buttonGet;
    public EditText editTextCredential;
    public EditText editTextFullName;
    public EditText editTextTransLine;
    public TextView textViewCredential;

    public VolleySingleton volleySingleton;

    public static final String TAG = AssignActivityControlAccess.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_control_access);

        bindUI();
    }

    public void bindUI() {
        buttonSend = findViewById(R.id.assignControlAccessButtonSend);
        buttonCancel = findViewById(R.id.assignControlAccessButtonCancel);
        buttonGet = findViewById(R.id.assignControlAccessButtonGet);
        editTextCredential = findViewById(R.id.assignControlAccessEditTextCredential);
        editTextFullName = findViewById(R.id.assignControlAccessEditTextFullName);
        editTextTransLine = findViewById(R.id.assignControlAccessEditTextTransLine);
        textViewCredential = findViewById(R.id.assignControlAccessTextViewCredential);
    }

    public void showInvalidCodeError() {
        textViewCredential.setTextColor(Color.RED);
        textViewCredential.setText("Código inválido!");
    }

    public void disableAuthFields() {
        //editTextFullName.setFocusable(false);
        editTextFullName.setEnabled(false);
        editTextTransLine.setEnabled(false);
        //editTextTransLine.setFocusable(false);
    }

    public void showServerError() {
        textViewCredential.setTextColor(Color.RED);
        textViewCredential.setText("Server error!");
    }

    public void getDriverInfo(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.DRIVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            SCDriver driver = SCDriver.getInstance();

                            disableAuthFields();

                            // Extract data received from JSON
                            driver.setPicture(jsonObject.isNull("picture") ? URLs.RC_LOGO : jsonObject.getString("picture"));
                            driver.setDriverName(jsonObject.isNull("driver_name") ? "driver_name_null" : jsonObject.getString("driver_name"));
                            driver.setFiscalCode(jsonObject.isNull("fiscal_code") ? "fiscal_code_null" : jsonObject.getString("fiscal_code"));
                            driver.setCompanyName(jsonObject.isNull("company_name") ? "company_name_null" : jsonObject.getString("company_name"));
                            driver.setPhoneNumber(jsonObject.isNull("phone_number") ? "phone_number_null" : jsonObject.getString("phone_number"));
                            driver.setDisctum(jsonObject.isNull("disctum") ? -1 : jsonObject.getInt("disctum"));
                            driver.setDriverId(jsonObject.isNull("driver_id") ? -1 : jsonObject.getInt("driver_id"));
                            driver.setTranslineId(jsonObject.isNull("transline_id") ? -1 : jsonObject.getInt("transline_id"));
                            driver.setUsrMovil(jsonObject.isNull("usr_movil") ? false : jsonObject.getBoolean("usr_movil"));
                        } catch (JSONException err) {
                            // Invalid code
                            showInvalidCodeError();
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
                params.put("token", "a0c272f8-6016-4e2b-a515-7e03ed200c56");
                params.put("parameters", "{\"credential\":\"1064471\"}");

                return params;
            }
        };

        volleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
