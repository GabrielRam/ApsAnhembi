package com.aps_anhembi.aps_anhembi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class AddLocalActivity extends AppCompatActivity  {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            final String url = "WebService/rest/local/cadastrar"; //URL String do web service para cadastro de locais

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            //tratamento da response
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //tratamento de erros
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("titulo", "Alif"); //txt do titulo
                    params.put("latitude", "http://itsalif.info"); //txt latitude
                    params.put("longitude", "Alif"); //txt longitude
                    params.put("id_usuario", "Alif"); //pegar id usuario do firebase

                    return params;
                }
            };
            queue.add(postRequest);
        }
}
