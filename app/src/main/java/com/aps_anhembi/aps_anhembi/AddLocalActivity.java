package com.aps_anhembi.aps_anhembi;

        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.util.HashMap;
        import java.util.Map;


public class AddLocalActivity extends AppCompatActivity  {

    public Location location; // location
    protected LocationManager locationManager;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    TextView tLongitude;
    Button cadastrar;
    TextView tLatitude;
    EditText titulo;
    double latitude; // latitude
    double longitude; // longitude

    private final Context mContext = this;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    boolean canGetLocation = false;

    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);
        Button cadastrar=findViewById(R.id.cadastrarLocal);
        tLongitude=findViewById(R.id.logitudeAdd);
        tLatitude=findViewById(R.id.latitudeAdd);
        titulo=findViewById(R.id.tituloAdd);
        tLongitude.setVisibility(View.VISIBLE);
        tLatitude.setVisibility(View.VISIBLE);
        Location location = getLocation();
        if(location !=null){
        tLatitude.setText(Double.toString(location.getLatitude()));
        tLongitude.setText(Double.toString(location.getLongitude()));
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //esse código deve ser executado dentro do evento de click do botão cadastrar
                final String url = "WebService/rest/local/cadastrar"; //URL String do web service para cadastro de locais

                RequestQueue queue = Volley.newRequestQueue(AddLocalActivity.this);

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(AddLocalActivity.this,"Enviado",Toast.LENGTH_SHORT).show();
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
                        params=construir();

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
        }
    }

    public Location getLocation() {

        Intent locationIntent = new Intent(getApplicationContext(), AddLocalActivity.class);
        PendingIntent locationPendingIntent = PendingIntent.getService(
                getApplicationContext(),
                0,
                locationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Verifica se tem permissão de localização, caso não tenha pede que usuário conceda a permissão
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }

        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationPendingIntent);

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationPendingIntent);

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public Map<String, String> construir(){
        Map<String, String>  params = new HashMap<String, String>();

        //Adiciona os parametros para POST de criação de local
        params.put("titulo", titulo.toString() ); //txt do titulo
        params.put("latitude", tLatitude.toString()); //txt latitude
        params.put("longitude", tLongitude.toString()); //txt longitude
        //params.put("id_usuario", ); //pegar id usuario do firebase
        return params;
    }

}
