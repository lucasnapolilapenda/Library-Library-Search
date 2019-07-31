package com.library.library.request.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

public class RequestSoap {

    public String getPublisher (String publisherName) throws Exception_Exception, MalformedURLException {
        URL url = new URL ("http://localhost:8092/publishers/publisher?wsdl");
        QName qname = new QName("http://publisher.search.library.com/", "PublisherImpService");
        Service service = Service.create(url, qname);
        PublisherInterface publisher = service.getPort(PublisherInterface.class);

        Map<String, Object> requestContext = ((BindingProvider)publisher).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://localhost:8092/publishers/publisher?wsdl");
        Map<String, List<String>> requestHeaders = new HashMap<String, List<String>>();
        requestHeaders.put("username", Collections.singletonList("book-service"));
        requestHeaders.put("Password", Collections.singletonList("book-service-secure-password-1234"));
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);

        Publisher publisher1 = new Publisher();
        publisher1.setPublisherName(publisherName);
        return publisher.getPublisherInfo(publisher1);
    }

}
