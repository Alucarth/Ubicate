package com.david.ubicate;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    Button btn;
    EditText print;
//    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        print =(EditText) findViewById(R.id.txtEventos);
        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
                print.append("\n\n"+getHora()+" "+"gps habilitado :) " + provider);
//                Toast.makeText(MainActivity.this,
//                        "Provider enabled: " + provider, Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                print.append("\n\n"+getHora()+" "+"gps desahibilato XD " + provider);
            }

            @Override
            public void onLocationChanged(Location location) {
                // Do work with new location. Implementation of this method will be covered later.
//                doWorkWithNewLocation(location);
                print.append("\n\n"+getHora()+" "+"Nueva localizacion: "
                                                +"\n latitud"+location.getLatitude()
                                                +"\n longitud"+location.getLongitude()
                                                +"\n altitud"+location.getAltitude()
                                                );
//                Toast.makeText(MainActivity.this,"latitud"+location.getLatitude()+"longitud"+location.getLongitude(),Toast.LENGTH_SHORT).show();
            }
        };

        long minTime = 5 * 1000; // Minimum time interval for update in seconds, i.e. 5 seconds.
        long minDistance = 10; // Minimum distance change for update in meters, i.e. 10 meters.

// Assign LocationListener to LocationManager in order to receive location updates.
// Acquiring provider that is used for location updates will also be covered later.
// Instead of LocationListener, PendingIntent can be assigned, also instead of
// provider name, criteria can be used, but we won't use those approaches now.
        locationManager.requestLocationUpdates(getProviderName(), minTime,
                minDistance, locationListener);

        print =(EditText) findViewById(R.id.txtEventos);
        btn = (Button) findViewById(R.id.btnGps);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(gps.canGetLocation())
//                {
//                    String datos = "longitud "+locationManager.get.getLongitude()+"\n latitud"+gps.getLatitude();

                    Toast.makeText(getBaseContext(), "Enviando via web Service XD", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getBaseContext(), "no funciona ooooh por que XD ", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    gps.showSettingsAlert();
//
//                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * Get provider name.
     * @return Name of best suiting provider.
     * */
    String getProviderName() {
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW); // Chose your desired power consumption level.
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.
        criteria.setSpeedRequired(true); // Chose if speed for first location fix is required.
        criteria.setAltitudeRequired(false); // Choose if you use altitude.
        criteria.setBearingRequired(false); // Choose if you use bearing.
        criteria.setCostAllowed(false); // Choose if this provider can waste money :-)

        // Provide your criteria and flag enabledOnly that tells
        // LocationManager only to return active providers.
        return locationManager.getBestProvider(criteria, true);
    }
    public String getHora()
    {
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String formattedDate = sdf.format(new Date());
        return formattedDate;
    }
}
