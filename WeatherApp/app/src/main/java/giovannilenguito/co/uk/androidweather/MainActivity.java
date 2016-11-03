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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new getWeather().execute();

        setContentView(R.layout.activity_main);

    }

    class getWeather extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... urls) {
            List<Object> studentList = new ArrayList<>();
            try{
                //URL of xml data
                URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                URLConnection conn = url.openConnection();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(conn.getInputStream());

                NodeList nodes = doc.getElementsByTagName("channel");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    NodeList FirstName = element.getElementsByTagName("title");
                    Element line = (Element) FirstName.item(0);
                    studentList.add(line.getTextContent());
                    System.out.println(line.getTextContent());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
