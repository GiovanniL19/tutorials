package giovannilenguito.co.uk.androidweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import giovannilenguito.co.uk.androidweather.Model.Weather;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new getWeather().execute();

        setContentView(R.layout.activity_main);

    }

    class getWeather extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... urls) {
            List<Object> weatherList = new ArrayList<>();
            try{
                String key = "e18a7372402f4236aceae23897fa1af9";
                String location = "London";

                //URL of xml data
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + key + "&mode=xml&units=metric");
                URLConnection conn = url.openConnection();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(conn.getInputStream());

                NodeList nodes = doc.getElementsByTagName("current");

                //Initialising weather object
                Weather weatherObject = new Weather();

                //loops all nodes
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    //GET NAME FROM XML AND SET TO OBJECT
                    NodeList nodeItem = element.getElementsByTagName("city");
                    weatherObject.setCity(((Element) nodeItem.item(0)).getAttribute("name"));

                    //GET COUNTRY FROM XML AND SET TO OBJECT
                    nodeItem = element.getElementsByTagName("country");
                    weatherObject.setCountry(nodeItem.item(0).getTextContent());

                    //GET COUNTRY FROM XML AND SET TO OBJECT
                    nodeItem = element.getElementsByTagName("temperature");
                    Element temperature = (Element) nodeItem.item(0);

                    weatherObject.setCurrentTemp(Double.parseDouble(temperature.getAttribute("value")));
                    weatherObject.setMinTemp(Double.parseDouble(temperature.getAttribute("min")));
                    weatherObject.setMaxTemp(Double.parseDouble(temperature.getAttribute("max")));


                    //GET WIND FROM XML AND SET TO OBJECT
                    nodeItem = element.getElementsByTagName("wind");
                    String windDes = nodeItem.item(0).getChildNodes().item(0).getAttributes().item(1).getNodeValue();
                    Double windSpeed = Double.parseDouble(nodeItem.item(0).getChildNodes().item(0).getAttributes().item(0).getNodeValue());
                    String windDirection = nodeItem.item(0).getChildNodes().item(2).getAttributes().item(2).getNodeValue();

                    weatherObject.setWindDescription(windDes);
                    weatherObject.setWindSpeed(windSpeed);
                    weatherObject.setWindDirection(windDirection);

                    //GET CLOUDS FROM XML AND SET TO OBJECT
                    nodeItem = element.getElementsByTagName("clouds");
                    weatherObject.setClouds(((Element) nodeItem.item(0)).getAttribute("name"));


                    //GET VISIBILITY FROM XML AND SET TO OBJECT
                    nodeItem = element.getElementsByTagName("visibility");
                    weatherObject.setVisibility(Double.parseDouble(((Element) nodeItem.item(0)).getAttribute("value")));

                    System.out.println(weatherObject.toString());
                    weatherList.add(weatherObject);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
