
package webservice;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2015-04-21T11:58:14.871+02:00
 * Generated source version: 2.5.1
 * 
 */
public final class WeatherSoap_WeatherSoap_Client {

    private static final QName SERVICE_NAME = new QName("http://ws.cdyne.com/WeatherWS/", "Weather");

    private WeatherSoap_WeatherSoap_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = Weather.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        Weather ss = new Weather(wsdlURL, SERVICE_NAME);
        WeatherSoap port = ss.getWeatherSoap();  
        
        {
        System.out.println("Invoking getWeatherInformation...");
        webservice.ArrayOfWeatherDescription _getWeatherInformation__return = port.getWeatherInformation();
        System.out.println("getWeatherInformation.result=" + _getWeatherInformation__return);


        }
        {
        System.out.println("Invoking getCityWeatherByZIP...");
        java.lang.String _getCityWeatherByZIP_zip = "";
        webservice.WeatherReturn _getCityWeatherByZIP__return = port.getCityWeatherByZIP(_getCityWeatherByZIP_zip);
        System.out.println("getCityWeatherByZIP.result=" + _getCityWeatherByZIP__return);


        }
        {
        System.out.println("Invoking getCityForecastByZIP...");
        java.lang.String _getCityForecastByZIP_zip = "";
        webservice.ForecastReturn _getCityForecastByZIP__return = port.getCityForecastByZIP(_getCityForecastByZIP_zip);
        System.out.println("getCityForecastByZIP.result=" + _getCityForecastByZIP__return);


        }

        System.exit(0);
    }

}
