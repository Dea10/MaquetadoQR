package com.example.maquetadoqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.maquetadoqr.Config.Constants;
import com.example.maquetadoqr.Database.AppDatabase;
import com.example.maquetadoqr.Entities.EventConfiguration;
import com.example.maquetadoqr.Volley.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScannerActivity extends AppCompatActivity {

    public AnimationDrawable scanner_animation;
    public ImageView iv_header;
    public ImageView iv_logo;
    public ImageView iv_anim;
    public TextView tv_name;
    public TextView tv_counter;
    public Button test;

    public Intent intent;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        bindUI();

        iv_anim.setBackgroundResource(R.drawable.scanner_animation);
        scanner_animation = (AnimationDrawable) iv_anim.getBackground();
        //Instanciar la base de datos
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constants.DB_NAME).allowMainThreadQueries().build();
        loadResources();
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
        test = findViewById(R.id.button);
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

    private void loadResources() {
        String url = getResources().getString(R.string.base_url)+"japi/get_feature_configuration_by_user_and_category/";


        StringRequest jsonObjRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                EventConfiguration eventConfiguration = new EventConfiguration( jsonObject.getInt("feature_id"),
                                        jsonObject.getString("feature_key"),
                                        jsonObject.getInt("order"),
                                        jsonObject.getString("resource_name"),
                                        jsonObject.getBoolean("isauthorized"),
                                        jsonObject.getString("feature_configuration"));
                                //Inserta los features recibidos
                                db.eventConfigurationDAO().insertConfiguration(eventConfiguration);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Errorl",error.toString());
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", "9f7bfb0a-715d-4bd3-834b-d5b649f386e3");
                params.put("parameters", "{\"feature_category_key\":\"REGISTER\"}");
                return params;
            }

        };
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjRequest);


    }
}
