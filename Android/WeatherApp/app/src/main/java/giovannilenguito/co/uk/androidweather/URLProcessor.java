package giovannilenguito.co.uk.androidweather;

import org.w3c.dom.Document;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by giovannilenguito on 03/11/2016.
 */

public class URLProcessor {
    public static Document getDocument(final URL url){
        try {
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            return builder.parse(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
