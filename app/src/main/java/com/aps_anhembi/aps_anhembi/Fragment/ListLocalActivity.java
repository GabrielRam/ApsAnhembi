package com.aps_anhembi.aps_anhembi.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aps_anhembi.aps_anhembi.Model.Local;
import com.aps_anhembi.aps_anhembi.R;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class ListLocalActivity extends Fragment {
    ListView tlistLocal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_local_fragment, null);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String url = "WebService/rest/local/listar";
        final List<Local> tLocais = new ArrayList<Local>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        tlistLocal=view.findViewById(R.id.list_locais);
        final Local localAux=null;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            for (int i = 0; i < response.length(); i++) {
                                String latitude=response.getString("latitude");
                                localAux.setLatitude(latitude);
                                String longitude=response.getString("longitude");
                                localAux.setLongitude(longitude);
                                String titulo=response.getString("titulo");
                                localAux.setTitulo(titulo);
                                tLocais.add(localAux);

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getContext(),
                                    "Erro: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tratamento de erros
                    }
                }
        );

        queue.add(getRequest);
        if(!tLocais.isEmpty()){
            ArrayAdapter<Local> adapter = new ArrayAdapter<Local>(getContext(),
                    android.R.layout.simple_list_item_1, tLocais);

            tlistLocal.setAdapter(adapter);
        }
    }


}
