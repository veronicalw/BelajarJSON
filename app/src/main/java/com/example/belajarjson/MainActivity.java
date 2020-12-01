package com.example.belajarjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textViewHasil;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHasil = findViewById(R.id.textView_result);
        Button tombolParse = findViewById(R.id.tombol_parse);

        mQueue = Volley.newRequestQueue(this);
        tombolParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    private void jsonParse(){
//        String url = "https://run.mocky.io/v3/13422794-156e-41de-89a9-5bfb8cfc1f82";
//        String url = "https://run.mocky.io/v3/627de116-5c93-4557-9f7e-9bd86905ae06";
//        String url = "https://run.mocky.io/v3/c4140d37-5af6-46b7-868c-681dfecc1da2";
//        String url ="https://run.mocky.io/v3/0296ccb5-2327-4599-a7df-59f96f53b619";
//        String url="https://run.mocky.io/v3/53d7374f-62da-46e8-ab21-6f30a52591a1";
        String url = "https://run.mocky.io/v3/6970085f-dec5-4768-aa3e-d5b2e32e36ab";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("mahasiswa");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mahasiswa = jsonArray.getJSONObject(i);
                        String namaDepan = mahasiswa.getString("firstname");
                        int umur = mahasiswa.getInt("age");
                        String email = mahasiswa.getString("mail");

                        textViewHasil.append(namaDepan + ", " + String.valueOf(umur) + ", " + email + "\n\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });
        mQueue.add(request);
    }
}