package com.library.library.request.soap;

/**
 *
 * @author Lucas Napoli
 * V1.0
 * Library Microservices
 * Service search Consolidator
 */

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PublisherInterface", targetNamespace = "http://publisher.search.library.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublisherInterface {


    /**
     * 
     * @param arg0 arguments
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publisher.search.library.com/PublisherInterface/getPublisherInfoRequest", output = "http://publisher.search.library.com/PublisherInterface/getPublisherInfoResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://publisher.search.library.com/PublisherInterface/getPublisherInfo/Fault/Exception")
    })
    public String getPublisherInfo(
        @WebParam(name = "arg0", partName = "arg0")
        Publisher arg0)
        throws Exception_Exception
    ;

}
