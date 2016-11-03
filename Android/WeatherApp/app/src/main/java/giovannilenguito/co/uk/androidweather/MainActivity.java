package giovannilenguito.co.uk.androidweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import giovannilenguito.co.uk.androidweather.controller.WeatherDAOController;

public class MainActivity extends AppCompatActivity {

    List<Object> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherDAOController WDAOC = new WeatherDAOController();

        String key = "e18a7372402f4236aceae23897fa1af9";
        String location = "London";

        //URL of xml data
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + key + "&mode=xml&units=metric");
            WDAOC.execute(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

    }


}
