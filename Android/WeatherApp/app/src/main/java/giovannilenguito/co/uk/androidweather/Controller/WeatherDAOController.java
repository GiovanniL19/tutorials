package giovannilenguito.co.uk.androidweather.controller;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import giovannilenguito.co.uk.androidweather.URLProcessor;
import giovannilenguito.co.uk.androidweather.model.Weather;

/**
 * Created by giovannilenguito on 03/11/2016.
 */

public class WeatherDAOController extends AsyncTask<Object, Object, Weather>{
    protected Weather doInBackground(Object... params) {
        try{
            Document doc = URLProcessor.getDocument((URL) params[0]);
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

                System.out.println(weatherObject.toString());
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
