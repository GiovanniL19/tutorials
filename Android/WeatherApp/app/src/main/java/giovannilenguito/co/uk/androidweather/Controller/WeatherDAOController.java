package giovannilenguito.co.uk.androidweather.controller;

import android.os.AsyncTask;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import giovannilenguito.co.uk.androidweather.URLProcessor;
import giovannilenguito.co.uk.androidweather.model.Weather;


/**
 * Created by giovannilenguito on 03/11/2016.
 */

public class WeatherDAOController extends AsyncTask<Object, Object, Weather>{
    protected Weather doInBackground(Object... params) {
        URL url = (URL) params[0];

        if(params[1] == "JSON"){
            return parseJSON(url);
        }else {
            return parseXML(url);
        }
    }

    public String getJSONByURL(URL url, int timeout) {
        HttpURLConnection connection = null;
        try {
            //make the request
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //connect
            connection.connect();

            //get status code
            int statusCode = connection.getResponseCode();

            switch (statusCode) {
                case 200:
                    System.out.println("All okay");
                case 201:
                    BufferedReader buffRead = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = buffRead.readLine()) != null) {
                        stringBuilder.append(line+"\n");
                    }
                    buffRead.close();

                    return stringBuilder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Weather parseJSON(URL url) {
        try{
            //gets json as string, has timeout of 60 seconds
            String json = getJSONByURL(url, 60000);

            //use GSON to convert json into a
            if(json != null) {
                //Gson gson = new Gson();
                //Weather weatherObject = gson.fromJson(json, Weather.class);

                Map jsonMap = new Gson().fromJson(json, Map.class);

                Weather weatherObject = new Weather();
                weatherObject.setCity(jsonMap.get("name").toString());

                Map clouds = (Map) jsonMap.get("clouds");
                weatherObject.setClouds(Math.round(Double.parseDouble((clouds.get("all").toString()).toString())) + "%");

                Map wind = (Map) jsonMap.get("wind");
                weatherObject.setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
                weatherObject.setWindDescription(wind.get("deg").toString() + "°");

                Map main = (Map) jsonMap.get("main");
                Double temp = Double.parseDouble(main.get("temp").toString());
                Double tempMin = Double.parseDouble(main.get("temp_min").toString());
                Double tempMax = Double.parseDouble(main.get("temp_max").toString());

                weatherObject.setCurrentTemp(temp);
                weatherObject.setMinTemp(tempMin);
                weatherObject.setMinTemp(tempMax);

                Map sys = (Map) jsonMap.get("sys");
                weatherObject.setCountry(sys.get("country").toString());

                weatherObject.setVisibility(Math.round(Double.parseDouble(jsonMap.get("visibility").toString())));

                ArrayList weatherMain = (ArrayList) jsonMap.get("weather");
                Map weather = (Map) weatherMain.get(0);
                weatherObject.setDescription(weather.get("main").toString());

                //HOW TO GET DATA FROM ARRAY
                /*
                for (Object weather : weatherMain) {
                    Map weatherMap = (Map) weather;
                    weatherMap.get("main");
                }
                */

                return weatherObject;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private Weather parseXML(URL url) {
        try{
            Document doc = URLProcessor.getDocument(url);
            if(doc != null) {
                NodeList nodes = doc.getElementsByTagName("current");

                //Initialising weather object
                Weather weatherObject = new Weather();

                //loops all nodes
                Element element = (Element) nodes.item(0);

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

                return weatherObject;
            }else{
                System.out.println("No Document");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
