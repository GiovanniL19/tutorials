package giovannilenguito.co.uk.androidweather.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import giovannilenguito.co.uk.androidweather.R;
import giovannilenguito.co.uk.androidweather.controller.WeatherDAOController;
import giovannilenguito.co.uk.androidweather.model.Weather;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "e18a7372402f4236aceae23897fa1af9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather("London", "JSON", getCurrentFocus());
    }

    public Weather getXMLParsedData(String location, String objectType) throws MalformedURLException {
        try {
            return new WeatherDAOController().execute(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + API_KEY + "&mode="+ objectType + "&units=metric"), "JSON").get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void getWeather(String location, String objectType, View view){
        //URL of xml data
        try {
            Weather weather = getXMLParsedData(location, objectType);
            if( weather != null ){
                //set ui
                TextView clouds = (TextView)findViewById(R.id.clouds);
                clouds.setText(weather.getClouds());

                TextView country = (TextView)findViewById(R.id.country);
                country.setText(weather.getCountry());

                TextView loc = (TextView)findViewById(R.id.locaion);
                loc.setText(location);

                TextView maxTemp = (TextView)findViewById(R.id.maxTemp);
                maxTemp.setText(String.valueOf(Math.round(weather.getMaxTemp())) + "°C");

                TextView minTemp = (TextView)findViewById(R.id.minTemp);
                minTemp.setText(String.valueOf(Math.round(weather.getMinTemp())) + "°C");

                TextView temp = (TextView)findViewById(R.id.temp);
                temp.setText(String.valueOf(Math.round(weather.getCurrentTemp()))  + "°C");

                TextView windDes = (TextView)findViewById(R.id.windDes);
                windDes.setText(weather.getWindDescription());

                TextView windSpe = (TextView)findViewById(R.id.windSpeed);
                windSpe.setText(String.valueOf(Math.round(weather.getWindSpeed())) + "MPH");

                TextView vis = (TextView)findViewById(R.id.visibility);
                vis.setText(String.valueOf(weather.getVisibility()));

                TextView des = (TextView)findViewById(R.id.description);
                des.setText(String.valueOf(weather.getDescription()));

            }else{
                //snack bar saying cannot get data
                Snackbar.make(findViewById(android.R.id.content), "Incorrect Location", Snackbar.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            //snack bar cannot get weather
            Snackbar.make(findViewById(android.R.id.content), "Unable to get weather, try again later", Snackbar.LENGTH_LONG).show();
        }
    }

    public void weatherRequest(View view){
        final String location = ((EditText) findViewById(R.id.weatherLocation)).getText().toString();
        getWeather(location, "JSON", view);
        View viewFocus = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
