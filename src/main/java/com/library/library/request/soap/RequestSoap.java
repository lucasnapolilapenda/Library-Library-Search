package com.library.library.request.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class RequestSoap {

    public String getPublisher (String publisherName) throws Exception_Exception, MalformedURLException {
        URL url = new URL ("http://localhost:8092/publishers/publisher?wsdl");
        QName qname = new QName("http://publisher.search.library.com/", "PublisherImpService");
        Service service = Service.create(url, qname);
        PublisherInterface publisher = service.getPort(PublisherInterface.class);

        Publisher publisher1 = new Publisher();
        publisher1.setPublisherName(publisherName);
        return publisher.getPublisherInfo(publisher1);
    }

}
