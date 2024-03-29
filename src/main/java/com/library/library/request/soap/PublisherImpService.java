package com.library.library.request.soap;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PublisherImpService", targetNamespace = "http://publisher.search.library.com/", wsdlLocation = "http://localhost:9595/LibrarySearchPublisher_war/publisher?wsdl")
public class PublisherImpService
    extends Service
{

    private final static URL PUBLISHERIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException PUBLISHERIMPSERVICE_EXCEPTION;
    private final static QName PUBLISHERIMPSERVICE_QNAME = new QName("http://publisher.search.library.com/", "PublisherImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9595/LibrarySearchPublisher_war/publisher?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PUBLISHERIMPSERVICE_WSDL_LOCATION = url;
        PUBLISHERIMPSERVICE_EXCEPTION = e;
    }

    public PublisherImpService() {
        super(__getWsdlLocation(), PUBLISHERIMPSERVICE_QNAME);
    }

    public PublisherImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PUBLISHERIMPSERVICE_QNAME, features);
    }

    public PublisherImpService(URL wsdlLocation) {
        super(wsdlLocation, PUBLISHERIMPSERVICE_QNAME);
    }

    public PublisherImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PUBLISHERIMPSERVICE_QNAME, features);
    }

    public PublisherImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PublisherImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PublisherInterface
     */
    @WebEndpoint(name = "PublisherImpPort")
    public PublisherInterface getPublisherImpPort() {
        return super.getPort(new QName("http://publisher.search.library.com/", "PublisherImpPort"), PublisherInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PublisherInterface
     */
    @WebEndpoint(name = "PublisherImpPort")
    public PublisherInterface getPublisherImpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://publisher.search.library.com/", "PublisherImpPort"), PublisherInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PUBLISHERIMPSERVICE_EXCEPTION!= null) {
            throw PUBLISHERIMPSERVICE_EXCEPTION;
        }
        return PUBLISHERIMPSERVICE_WSDL_LOCATION;
    }

}
