package webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2015-04-21T11:58:14.907+02:00
 * Generated source version: 2.5.1
 * 
 */
@WebServiceClient(name = "Weather", 
                  wsdlLocation = "http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL",
                  targetNamespace = "http://ws.cdyne.com/WeatherWS/") 
public class Weather extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.cdyne.com/WeatherWS/", "Weather");
    public final static QName WeatherSoap12 = new QName("http://ws.cdyne.com/WeatherWS/", "WeatherSoap12");
    public final static QName WeatherSoap = new QName("http://ws.cdyne.com/WeatherWS/", "WeatherSoap");
    public final static QName WeatherHttpPost = new QName("http://ws.cdyne.com/WeatherWS/", "WeatherHttpPost");
    public final static QName WeatherHttpGet = new QName("http://ws.cdyne.com/WeatherWS/", "WeatherHttpGet");
    static {
        URL url = null;
        try {
            url = new URL("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Weather.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public Weather(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Weather(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Weather() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Weather(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Weather(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public Weather(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns WeatherSoap
     */
    @WebEndpoint(name = "WeatherSoap12")
    public WeatherSoap getWeatherSoap12() {
        return super.getPort(WeatherSoap12, WeatherSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WeatherSoap
     */
    @WebEndpoint(name = "WeatherSoap12")
    public WeatherSoap getWeatherSoap12(WebServiceFeature... features) {
        return super.getPort(WeatherSoap12, WeatherSoap.class, features);
    }
    /**
     *
     * @return
     *     returns WeatherSoap
     */
    @WebEndpoint(name = "WeatherSoap")
    public WeatherSoap getWeatherSoap() {
        return super.getPort(WeatherSoap, WeatherSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WeatherSoap
     */
    @WebEndpoint(name = "WeatherSoap")
    public WeatherSoap getWeatherSoap(WebServiceFeature... features) {
        return super.getPort(WeatherSoap, WeatherSoap.class, features);
    }
    /**
     *
     * @return
     *     returns WeatherHttpPost
     */
    @WebEndpoint(name = "WeatherHttpPost")
    public WeatherHttpPost getWeatherHttpPost() {
        return super.getPort(WeatherHttpPost, WeatherHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WeatherHttpPost
     */
    @WebEndpoint(name = "WeatherHttpPost")
    public WeatherHttpPost getWeatherHttpPost(WebServiceFeature... features) {
        return super.getPort(WeatherHttpPost, WeatherHttpPost.class, features);
    }
    /**
     *
     * @return
     *     returns WeatherHttpGet
     */
    @WebEndpoint(name = "WeatherHttpGet")
    public WeatherHttpGet getWeatherHttpGet() {
        return super.getPort(WeatherHttpGet, WeatherHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WeatherHttpGet
     */
    @WebEndpoint(name = "WeatherHttpGet")
    public WeatherHttpGet getWeatherHttpGet(WebServiceFeature... features) {
        return super.getPort(WeatherHttpGet, WeatherHttpGet.class, features);
    }

}
